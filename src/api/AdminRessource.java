package api;

import model.Customer;
import model.IRoom;
import service.Services;

import java.util.List;

public class AdminRessource {
    private static AdminRessource adminRessource;
    private static final Services services = new Services();

    private AdminRessource() {}

    public static AdminRessource getInstance() {
        if (adminRessource == null) {
            adminRessource = new AdminRessource();
        }
        return adminRessource;
    }

    public Customer getCustomer(String email) {
        return services.getCustomerService().getCustomer(email);
    }

    public void addRooms(List<IRoom> rooms) {
        for (IRoom room :
                rooms) {
            services.getReservationService().addRoom(room);
        }
    }

    public List<IRoom> getAllRooms() {
        return services.getReservationService().getRooms();
    }


    public List<Customer> getAllCustomers() {
        return services.getCustomerService().getAllCustomers();
    }

    public void displayAllReservations() {
        services.getReservationService().printAllReservations();
    }
}
