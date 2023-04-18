package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        // реализуйте алгоритм здесь
        Util.getConnection();

        UserService userService = new UserServiceImpl();

        while (true) {
            System.out.println("" +
                    "1. CREATE TABLE :\n" +
                    "2. DROP TABLE :\n" +
                    "3. SAVE USERS :\n" +
                    "4. REMOVE USER BY ID :\n" +
                    "5. GET ALL USERS :\n" +
                    "6. CLEAN USERS TABLE \n");
            System.out.println("ENTER BY COMMANDS :");
           int num = new Scanner(System.in).nextInt();
            switch (num) {
               case 1 -> userService.createUsersTable();
                case 2 -> userService.dropUsersTable();
                case 3 -> {

                        userService.saveUser("Anyschema", "Balaclava", (byte) 19);
                        userService.saveUser("Orality", "Digit", (byte) 20);

                }
                case 4 -> {
                    System.out.println("Enter by id :");
                    long id = new Scanner(System.in).nextLong();
                    userService.removeUserById(id);
                }
                case 5 -> System.out.println(userService.getAllUsers());
                case 6 -> userService.cleanUsersTable();
            }

        }
    }
}
