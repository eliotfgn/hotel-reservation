package app;

import java.util.Scanner;

public class AdminMenu {
    private static AdminMenu adminMenu;

    private AdminMenu() {}

    public static AdminMenu getInstance() {
        if (adminMenu == null) {
            adminMenu = new AdminMenu();
        }
        return adminMenu;
    }

    public int adminMenuChoice() {
        System.out.println("""
                --------------------------------------
                1. See all Customers
                2. See all Rooms
                3. See all Reservations
                4. Add a Room
                5. Back to Main Menu
                --------------------------------------""");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }


}
