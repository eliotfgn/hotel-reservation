package app;

import java.util.Scanner;

public class MainMenu {
    private static MainMenu mainMenu;

    private MainMenu() {}

    public static MainMenu getInstance() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }

    public int mainMenuChoice() {
        System.out.println("""
                --------------------------------------
                1. Find and reserve a room
                2. See my reservations
                3. Create an account
                4. Admin
                5. Exit
                --------------------------------------""");

        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }
}
