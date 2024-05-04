package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/

public class Solution implements Externalizable {

    public static void main(String[] args) {



        try {
            /*FileOutputStream fos = new FileOutputStream("C:\\Java Coding\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2014\\test.bat");

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            savedObject.writeExternal(oos);

            FileInputStream inp = new FileInputStream("C:\\Java Coding\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2014\\test.bat");

            ObjectInputStream iip = new ObjectInputStream(inp);
            loadedObject.readExternal(iip);*/



            FileOutputStream fos = new FileOutputStream("C:\\\\Java Coding\\\\JavaRushTasks\\\\2.JavaCore\\\\src\\\\com\\\\javarush\\\\task\\\\task20\\\\task2014\\\\test.bat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            FileInputStream fis = new FileInputStream("C:\\\\Java Coding\\\\JavaRushTasks\\\\2.JavaCore\\\\src\\\\com\\\\javarush\\\\task\\\\task20\\\\task2014\\\\test.bat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Solution savedObject = new Solution(34);
            oos.writeObject(savedObject);

            Solution loadedObject = (Solution) ois.readObject();

            fos.close();
            oos.close();
            fis.close();
            ois.close();

            System.out.println(savedObject.equals(loadedObject));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(string);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        string = (String) in.readObject();
    }

}
