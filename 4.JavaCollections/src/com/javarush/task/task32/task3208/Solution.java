package com.javarush.task.task32.task3208;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/* 
RMI-2
*/

public class Solution {
    public static Registry registry;

    public static Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (String bindingName : registry.list()) {
                    Animal service = (Animal) registry.lookup(bindingName);
                    service.printName();
                    service.speak();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
    });
    public static Thread SERVER_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {

            try {
                registry = LocateRegistry.createRegistry(2099);
                Cat cat = new Cat("cat");
                Dog dog  = new Dog("dog");
                Remote remoteCat = UnicastRemoteObject.exportObject(cat,0);
                Remote remoteDog = UnicastRemoteObject.exportObject(dog,0);
                registry.bind("negr",remoteCat);
                registry.bind("negr",remoteDog);
            } catch (RemoteException e) {
                System.err.println(e);
            } catch (AlreadyBoundException e) {
                System.err.println(e);
            }

        }
    });

    public static void main(String[] args) throws InterruptedException {
        SERVER_THREAD.start();
        Thread.sleep(1000);
        CLIENT_THREAD.start();
    }

}
