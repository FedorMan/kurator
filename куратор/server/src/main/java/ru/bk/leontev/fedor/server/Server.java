package ru.bk.leontev.fedor.server;




import com.thoughtworks.xstream.XStream;
import ru.bk.leontev.fedor.mvc.model.Group;
import ru.bk.leontev.fedor.mvc.model.Student;
import ru.bk.leontev.fedor.remoteService.RemoteService;

import java.io.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class Server implements RemoteService {

    public static final String BINDING_NAME = "server";
    private static String[] namesFilesGroups;
    static LinkedList<Group> savesGroups;


    /**
     * Получает список файлов групп из директории хранящей группы
     */

    public static void getNamesFilesGroups() {
        File direstory = new File("groups");
        namesFilesGroups = direstory.list();
    }

    /**
     * Создает массив групп хранимых в дирректории
     */

    public static void getGroups() {
        savesGroups = new LinkedList<Group>();
        for (int i = 0; i < namesFilesGroups.length; i++) {
            savesGroups.add(readerGroup(namesFilesGroups[i]));
        }
    }

    /**
     * Возвращает сохраненные на сервере группы
     */

    public LinkedList<Group> getAllGroups() {
        return savesGroups;
    }

    /**
     * записывает объект группы в файл
     */

    public void writerGroup(String nameFile, Group group) {

        try {
            XStream xoutstream = new XStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("groups\\\\" + nameFile)));
            ObjectOutputStream out = xoutstream.createObjectOutputStream(writer, "rootnode");
            out.writeObject(group);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getNamesFilesGroups();
        getGroups();
    }


    /**
     * Считывает объект группы из файла
     */

    public static Group readerGroup(String nameFile) {
        Group group = null;
        XStream xinstream = new XStream();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("groups\\\\" + nameFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ObjectInputStream in = xinstream.createObjectInputStream(reader);
            try {
                group = (Group) in.readObject();
                reader.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return group;
    }


    public void deleteGroup(String name) throws RemoteException {
        File direstory = new File("groups\\\\" + name);
        direstory.delete();
        getNamesFilesGroups();
        getGroups();
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    static String[] namesFiles;
    static LinkedList<Student> savesStudents;


    /**
     * записывает объект студента в файл
     */

    public void writer(String nameFile, Student student) {

        try {
            XStream xoutstream = new XStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("students\\\\" + nameFile)));
            ObjectOutputStream out = xoutstream.createObjectOutputStream(writer, "rootnode");
            out.writeObject(student);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Считывает объект студента из файла
     */

    public static Student reader(String nameFile) {
        Student student = null;
        XStream xinstream = new XStream();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("students\\\\" + nameFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ObjectInputStream in = xinstream.createObjectInputStream(reader);
            try {
                student = (Student) in.readObject();
                reader.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return student;
    }

    /**
     * Получает список файлов из директории хранящей студентов
     */

    public static void getNamesFiles() {
        File direstory = new File("students");
        namesFiles = direstory.list();
    }


    /**
     * Создает массив студентов хранимых в дирректории
     */

    public static void getStudents() {
        savesStudents = new LinkedList<Student>();
        for (int i = 0; i < namesFiles.length; i++) {
            savesStudents.add(reader(namesFiles[i]));
        }
    }
    public void writerStudent(String nameFile, Student student) throws RemoteException{
        writer(String.valueOf(student.getId()) + ".xml", student);
        getNamesFiles();
        getStudents();
    }
    public LinkedList<Student> getAllStudents() throws RemoteException{
        return savesStudents;
    }
    public void deleteStudent(String name) throws RemoteException{
        File direstory = new File("students\\\\" + name);
        direstory.delete();
        getNamesFiles();
        getStudents();
    }


    public static void main(String[] args) throws Exception {

        getNamesFilesGroups();
        getGroups();
        getNamesFiles();
        getStudents();
        System.out.print("Starting registry...");
        final Registry registry = LocateRegistry.createRegistry(2099);
        System.out.println(" OK");

        final RemoteService service = new Server();
        Remote stub = UnicastRemoteObject.exportObject(service, 0);

        System.out.print("Binding service...");
        registry.bind(BINDING_NAME, stub);
        System.out.println(" OK");

        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }


    }

}

//ru.bk.leontev.fedor.server.Server