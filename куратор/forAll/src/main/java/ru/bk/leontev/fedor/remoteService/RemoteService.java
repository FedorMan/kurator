package ru.bk.leontev.fedor.remoteService;

import ru.bk.leontev.fedor.mvc.model.Group;
import ru.bk.leontev.fedor.mvc.model.Student;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface RemoteService extends Remote {

    public void writerGroup(String nameFile, Group group) throws RemoteException;
    public LinkedList<Group> getAllGroups() throws RemoteException;
    public void deleteGroup(String name) throws RemoteException;


    public void writerStudent(String nameFile, Student student) throws RemoteException;
    public LinkedList<Student> getAllStudents() throws RemoteException;
    public void deleteStudent(String name) throws RemoteException;
}
