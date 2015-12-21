package ru.bk.leontev.fedor;

import ru.bk.leontev.fedor.mvc.controller.GroupController;
import ru.bk.leontev.fedor.mvc.controller.StudentController;
import ru.bk.leontev.fedor.mvc.view.View;
import ru.bk.leontev.fedor.remoteService.RemoteService;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws IOException {
        Registry registry;
        RemoteService service=null;
        try {
            registry = LocateRegistry.getRegistry("localhost", 2099);
            service = (RemoteService) registry.lookup("server");

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        View view = new View();
        new StudentController(view,service);
        new GroupController(view,service);
    }
}
//ru.bk.leontev.fedor.Main
