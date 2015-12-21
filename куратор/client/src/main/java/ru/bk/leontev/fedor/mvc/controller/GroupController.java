package ru.bk.leontev.fedor.mvc.controller;


import ru.bk.leontev.fedor.mvc.model.Group;
import ru.bk.leontev.fedor.mvc.view.View;
import ru.bk.leontev.fedor.remoteService.RemoteService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;

public class GroupController {
    private View view;
    static LinkedList<Group> savesGroups;
    static RemoteService service;
    static Registry registry;
    public GroupController(final View vew,RemoteService servic) {
        this.service=servic;
        this.view = vew;
        view.addTableGroupListener();
        view.addGroupListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!view.valideteGroup()) return;
                if (view.isRefactorGroup()) {
                    for (int i = 0; i < savesGroups.size(); i++) {
                        if (savesGroups.get(i).equals(view.getDedicatedGroup())) {
                            Group group = view.getGroupView().getGroup();
                            group.setId(savesGroups.get(i).getId());
                            try {
                                service.writerGroup(String.valueOf(group.getId()) + ".xml", group);
                                savesGroups = service.getAllGroups();
                            } catch (RemoteException e1) {
                                e1.printStackTrace();
                            }
                            view.getGroupView().clear();
                            view.getGroupView().updateTable();
                            view.setRefactorGroup(false);
                        }
                    }
                } else {
                    Group group = view.getGroupView().getGroup();
                    try {
                        service.writerGroup(String.valueOf(group.getId()) + ".xml", group);
                        savesGroups = service.getAllGroups();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                    view.getGroupView().clear();
                    view.getGroupView().updateTable();
                }
            }
        });
        this.view.deleteGroupListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < savesGroups.size(); i++) {
                    if (savesGroups.get(i).equals(view.getDedicatedGroup())) {
                        try {
                            service.deleteGroup(String.valueOf(savesGroups.get(i).getId()) + ".xml");
                            savesGroups = service.getAllGroups();
                        } catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            savesGroups = service.getAllGroups();
                        } catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
                        view.setRefactorGroup(false);
                        view.getGroupView().clear();
                        view.getGroupView().updateTable();
                        view.getStudentView().updateTable();

                    }
                }

            }
        });

        this.view.clearGroupListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.setRefactorGroup(false);
                view.getGroupView().clear();
                view.getStudentView().updateTable();
            }
        });
    }

    /**
     * Преобразует массив групп к двухмерному массиву для отображения
     */
    public static String[][] getStringTable() {
        try {
            registry = LocateRegistry.getRegistry("localhost", 2099);
            service = (RemoteService) registry.lookup("server");
            savesGroups = service.getAllGroups();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        String[][] table = new String[savesGroups.size()][2];
        for (int i = 0; i < savesGroups.size(); i++) {
            table[i] = savesGroups.get(i).arrStrings();
        }
        return table;
    }


}
