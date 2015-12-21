package ru.bk.leontev.fedor.mvc.controller;


import ru.bk.leontev.fedor.mvc.model.Student;
import ru.bk.leontev.fedor.mvc.view.View;
import ru.bk.leontev.fedor.remoteService.RemoteService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;


public class StudentController {
    static boolean isAll = true;
    static View view;
    static String[] namesFiles;
    static LinkedList<Student> savesStudents;
    static RemoteService service;
    static Registry registry;
    public StudentController(View vew, RemoteService servic) {
        this.service = servic;
        this.view = vew;
        this.view.addTableStudentListener();
        this.view.addSaveListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!view.valideteStudent()) return;
                if (view.isRefactorStudent()) {
                    for (int i = 0; i < savesStudents.size(); i++) {
                        if (savesStudents.get(i).equals(view.getDedicatedStudent())) {
                            Student student = view.getStudentView().getStudent();
                            student.setId(savesStudents.get(i).getId());
                            try {
                                service.writerStudent(String.valueOf(student.getId()) + ".xml", student);
                                savesStudents = service.getAllStudents();
                            } catch (RemoteException e1) {
                                e1.printStackTrace();
                            }

                            view.getStudentView().clear();
                            view.getGroupView().clear();
                            view.getStudentView().updateTable();
                        }
                    }
                } else {
                    Student student = view.getStudentView().getStudent();
                    try {
                        service.writerStudent(String.valueOf(student.getId()) + ".xml", student);
                        savesStudents = service.getAllStudents();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }

                    view.getStudentView().clear();
                    view.getStudentView().updateTable();
                }

            }
        });
        this.view.deleteStudentListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < savesStudents.size(); i++) {
                    if (savesStudents.get(i).equals(view.getDedicatedStudent())) {
                        try {
                            service.deleteStudent(savesStudents.get(i).getId() + ".xml");
                            savesStudents = service.getAllStudents();
                        } catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
                        view.setRefactorStudent(false);
                        view.getStudentView().clear();
                        view.getStudentView().updateTable();
                    }
                }
            }
        });

        this.view.clearStudentListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.setRefactorStudent(false);
                view.getStudentView().clear();
            }
        });


    }


    /**
     * Преобразует массив студентов к двухмерному массиву для отображения
     */
    public static String[][] getStringTable() {
        if (isAll) {
            try {
                registry = LocateRegistry.getRegistry("localhost", 2099);
                service = (RemoteService) registry.lookup("server");
                savesStudents = service.getAllStudents();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
        String[][] table = new String[savesStudents.size()][7];
        for (int i = 0; i < savesStudents.size(); i++) {
            table[i] = savesStudents.get(i).arrStrings();
        }
        isAll = true;
        return table;

    }

    /**
     * Удаляет из списка студенто всех кроме студентов заданной группы
     */
    public static void search() {
        isAll = false;
        try {
            savesStudents = service.getAllStudents();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < savesStudents.size(); i++) {
            if (savesStudents.get(i).getGroup() != view.getDedicatedGroup().getNumber()) {
                savesStudents.remove(i);
                i--;
            }
        }
    }

}
