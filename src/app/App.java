package app;

import api.AdminRessource;
import api.HotelRessource;
import model.*;

import java.util.*;

public class App {
    public static final HotelRessource hotelRessource = HotelRessource.getInstance();
    public static final AdminRessource adminRessource = AdminRessource.getInstance();
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        MainMenu mainMenu = MainMenu.getInstance();
        AdminMenu adminMenu = AdminMenu.getInstance();
        int choice = 0;
        int currentMenu = 1;
        boolean running = true;

        while (running) {
            if (currentMenu == 1) {
                choice = mainMenu.mainMenuChoice();
                switch (choice) {
                    case 1 -> bookARoom();
                    case 2 -> displayUserReservations();
                    case 3 -> createCustomer();
                    case 4 -> currentMenu = 2;
                    case 5 -> running = false;
                }
            }
            else if (currentMenu == 2) {
                choice = adminMenu.adminMenuChoice();
                switch (choice) {
                    case 1 -> displayAllCustomers();
                    case 2 -> displayAllRooms();
                    case 3 -> adminRessource.displayAllReservations();
                    case 4 -> addRooms();
                    case 5 -> currentMenu = 1;
                }
            }
        }
    }

    public static void bookARoom() {
        String userEmail;
        List<String> date;
        List<IRoom> availableRooms;
        IRoom room = null;
        Date checkIn, checkOut;
        Calendar calendar = Calendar.getInstance();
        Reservation reservation = null;

        System.out.println("Enter your email");
        while (true) {
            try {
                userEmail = scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }

        // First we'll check if there are free rooms for the period
        System.out.println("Enter the check-in date");
        date = Arrays.asList(scanner.nextLine().split("/"));
        calendar.set(Integer.parseInt(date.get(2)), Integer.parseInt(date.get(0)), Integer.parseInt(date.get(1)));
        checkIn = calendar.getTime();
        System.out.println("Enter the check-out date");
        date = Arrays.asList(scanner.nextLine().split("/"));
        calendar.set(Integer.parseInt(date.get(2)), Integer.parseInt(date.get(0)), Integer.parseInt(date.get(1)));
        checkOut = calendar.getTime();

        System.out.println("Choose a room to book");
        displayAllRooms();
        IRoom roomToBook = hotelRessource.getRoom(scanner.nextLine());
        availableRooms = hotelRessource.findARoom(checkIn, checkOut);

        if (availableRooms.contains(roomToBook)) {
            reservation = hotelRessource.bookARoom(hotelRessource.getCustomer(userEmail), roomToBook, checkIn, checkOut);
        }
        else {
            if (availableRooms.isEmpty()){
                System.out.println("Sorry :( There are no available rooms for your booking period!");
            }
            else {
                System.out.println("The room you want to book is unavailable for your booking period. Please check" +
                        "another room to book among the following ones");
                for (IRoom aroom : availableRooms) {
                    System.out.println(aroom.getRoomId()+" "+aroom.getRoomPrice()+" "+aroom.getRoomType());
                    }
                System.out.println("Do you want to book another room?");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    System.out.println("Choose a room to book");
                    roomToBook = hotelRessource.getRoom(scanner.nextLine());
                    reservation = hotelRessource.bookARoom(hotelRessource.getCustomer(userEmail), roomToBook,
                            checkIn, checkOut);
                }
            }
        }

        assert reservation != null;
        System.out.println(reservation.toString());
    }

    public static void createCustomer() {
        String email, firstname, lastname;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your email");
        while (true) {
            email = sc.nextLine();
            if (adminRessource.getAllCustomers().contains(adminRessource.getCustomer(email))) {
                System.out.println("This email is already used, please, choose another one.");
            }
            else {
                break;
            }
        }
        System.out.println("Enter your firstname");
        firstname = sc.nextLine();
        System.out.println("Enter your lastname");
        lastname = sc.nextLine();

        hotelRessource.createACustomer(email, firstname, lastname);

    }

    public static void displayUserReservations() {
        String userEmail;

        while (true) {
            System.out.println("Enter your email");

            try {
                userEmail = scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (!adminRessource.getAllCustomers().contains(adminRessource.getCustomer(userEmail))) {
                System.out.println("Sorry but this email is unknown");
                continue;
            }
            break;
        }

        List<Reservation> customerReservations = hotelRessource.getCustomersReservations(userEmail);
        System.out.println(customerReservations);

        for (Reservation reservation :
                customerReservations) {
            System.out.println("Room: " + reservation.getRoom().getRoomId() +
                    " || Type: " + reservation.getRoom().getRoomType() + " || From " + reservation.getCheckInDate() +
                    " to " + reservation.getCheckOutDate());
        }

    }

    public static void displayAllCustomers() {
        List<Customer> customers = adminRessource.getAllCustomers();

        for (Customer customer :
                customers) {
            System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getEmail());
        }
    }

    public static void displayAllRooms() {
        List<IRoom> rooms = adminRessource.getAllRooms();
        int i = 1;

        for (IRoom room :
                rooms) {
            System.out.println(room.getRoomId() + " " + room.getRoomType() + " " + room.getRoomPrice());
            i++;
        }
    }

    public static void addRooms() {
        List<IRoom> rooms = new ArrayList<>();
        String roomId;
        double roomPrice;
        RoomType roomType;
        int type;

        while (true) {
            System.out.println("Enter the room number");
            roomId = scanner.nextLine();
            System.out.println("Enter the price");
            roomPrice = scanner.nextDouble();
            do {
                System.out.println("Enter the type of the room (1. Single  2. Double)");
                type = scanner.nextInt();
                if (type == 1) {
                    roomType = RoomType.SINGLE;
                }
                else {
                    roomType = RoomType.DOUBLE;
                }
                System.out.println(type);
            } while ((type != 1) && (type != 2));

            rooms.add(new Room(roomId, roomPrice, roomType));

            System.out.println("Do you want to add more rooms? (y, n)");
            String addMore = scanner.next();
            if (addMore.equalsIgnoreCase("n")) {
                break;
            }
        }
        
        adminRessource.addRooms(rooms);

        scanner.nextLine();
    }
}