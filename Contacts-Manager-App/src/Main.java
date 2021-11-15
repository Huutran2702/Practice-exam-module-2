import file.FileService;
import model.Contact;
import model.Gender;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    private static List<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        contacts=FileService.read("data/contacts.csv");
        MenuView.showMenu();
        choice(contacts);
    }

    private static void choice(List<Contact> contacts) {
        System.out.print("Chọn chức năng: ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                showList(contacts);
                System.out.print("Nhấn enter để quay lại: ");
                String enter = sc.nextLine();
                MenuView.showMenu();
                choice(contacts);
            case "2":
                add();
                choice(contacts);
            case "3" :
            case "4" :

            case "5":
                searchContact();
                break;
            case "6":
                contacts = FileService.read("data/contacts.csv");
                System.out.println("Đã đọc file thành công");
                System.out.println("Nhấn enter để trở lại menu chính");
                sc.nextLine();
                MenuView.showMenu();
                choice(contacts);
                break;
            case "7":
                FileService.write("data/contacts.csv",contacts);
                System.out.println("Đã ghi file thành công");
                System.out.println("Nhấn enter để trở lại menu chính");
                sc.nextLine();
                MenuView.showMenu();
                choice(contacts);
                break;
            case "8":
                System.exit(0);
        }
    }

    private static void searchContact() {
        System.out.print("Nhập số điện thoại cần tìm kiếm: ");
        String phone = sc.nextLine();
        Contact contact =  getContactByPhone(phone);
        if (contact==null) {
            System.out.println("Số điện thoại không tồn tại");
            searchContact();
        } else {
            System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "Phone", "Group", "Name", "Gender", "Address");
            System.out.printf("%-15s%-15s%-15s%-15s%-15s\n",contact.getPhone(),contact.getGroup(),contact.getName(),contact.getGender(),contact.getAddress());
            System.out.printf("%-30s%-30s\n","Nhấn b để quay lại menu chính","Nhấn u để chỉnh sửa thông tin");
            String selected = sc.nextLine();
            switch (selected) {
                case "b":
                    MenuView.showMenu();
                    choice(contacts);
                    break;
                case "u":
                    update();
                    break;
                default:
                    System.out.println("Nhập sai lựa chọn - Mời bạn nhập lại");
            }
        }
    }

    private static void update() {
    }


    public static void showList(List<Contact> contacts) {
        int size = 0;
        if (contacts.size()>5) {
            size = 5;
        } else {
            size = contacts.size();
        }

        int start = 0;
        do {
            System.out.println("--------------------------- Danh sách ---------------------------");
            System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "Phone", "Group", "Name", "Gender", "Address");

            for (int i = start; i < size; i++) {
                System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", contacts.get(i).getPhone(), contacts.get(i).getGroup(), contacts.get(i).getName(),
                        contacts.get(i).getGender(), contacts.get(i).getAddress());
            }
            if (size < contacts.size()) {
                System.out.println("Nhấn enter để xem tiếp");
                String enter = sc.nextLine();
            } else {
                break;
            }

            if (contacts.size() - size >= 5) {
                start += 5;
                size += 5;
            } else {
                start += 5;
                size += contacts.size() - size;
            }

        }
        while (size <= contacts.size());


    }

    public static void add() {
        Contact newContact = new Contact();
        System.out.println("----------- Thêm danh bạ mới -----------");
        System.out.print("Nhập số điện thoại: ");
        String phone = sc.nextLine();
        System.out.print("Nhập nhóm danh bạ: ");
        String group = sc.nextLine();
        System.out.print("Nhập họ và tên: ");
        String name = sc.nextLine();
        System.out.print("Nhập giới tính: ");
        String gender = sc.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String address = sc.nextLine();
        System.out.print("Nhập ngày sinh: ");
        String birthday = sc.nextLine();
        System.out.print("Nhập email: ");
        String email = sc.nextLine();
        if (checkEnterFields(phone, group, name, gender, address, birthday, email)) {
            if (getContactByPhone(phone) == null) {
                newContact.setPhone(phone);
                newContact.setGroup(group);
                newContact.setName(name);
                newContact.setGender(Gender.valueOf(gender));
                newContact.setAddress(address);
                newContact.setBirthday(birthday);
                newContact.setEmail(email);
                contacts.add(newContact);
                System.out.println("Đã thêm danh bạ mới thành công");
                System.out.println("Nhấn Enter để về menu chính");
                sc.nextLine();
                MenuView.showMenu();

            } else {
                System.out.println("Danh bạ đã tồn tại");
                add();
            }
        } else {
            add();
        }
    }

    public static boolean checkEnterFields(String phone, String group, String name, String gender, String address, String birthday, String email) {
        if (phone.equals("")) {
            System.out.println("Số điện thoại không hợp lệ - Nhập lại số điện thoại");
            return false;
        } else if (!phone.matches("^[0-9\\-\\+]{9,15}$")) {
            System.out.println("Số điện thoại định dạng chưa đúng");
            return false;
        }
        if (group.equals("")) {
            System.out.println("Tên nhóm không hợp lệ - Nhập lại tên nhóm");
            return false;
        }
        if (name.equals("")) {
            System.out.println("Họ và tên không hợp lệ - Nhập lại họ và tên ");
            return false;
        }
        if (gender.equals("")) {
            System.out.println("Giới tính không hợp lệ - Nhập lại giới tính");
            return false;
        } else if (!gender.equals("MALE") && !gender.equals("FEMALE") && !gender.equals("OTHER")) {
            System.out.println("Giới tính không đúng - Nhập lại giới tính");
            return false;
        }
        if (address.equals("")) {
            System.out.println("Địa chỉ không hợp lệ - Nhập lại địa chỉ");
            return false;
        }
        if (email.equals("")) {
            System.out.println("Email không hợp lệ - Nhập lại email");
            return false;
        } else if (!email.matches("[A-Z,a-z0-9]+@+[a-z,0-9]+.+[a-z]")) {
            System.out.println("Định dạng email không đúng - Nhập lại email");
            return false;
        }
        return true;
    }

    public static Contact getContactByPhone(String phone) {
        for (Contact contact : contacts
        ) {
            if (contact.getPhone().equals(phone)) {
                return contact;
            }
        }
        return null;
    }
}
