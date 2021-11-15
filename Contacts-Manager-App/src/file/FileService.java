package file;

import model.Contact;
import model.Gender;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public static List<Contact> read(String filePath) {
        List<Contact> contacts = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line ;
            while ((line=bufferedReader.readLine())!=null) {
               String[] arr = line.split(",");
                Contact contact = new Contact(arr[0],arr[1],arr[2], Gender.valueOf(arr[3]),arr[4],arr[5],arr[6]);
                contacts.add(contact);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("File not found or file is corrupted");
        }
        return contacts;
    }
    public static void write(String filePath, List<Contact> contacts) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            for (Contact contact: contacts
                 ) {
                bufferedWriter.write(contact.toString()+"\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("File not found or file is corrupted");
        }
    }
}
