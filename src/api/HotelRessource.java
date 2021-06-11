package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.Services;

import java.util.Date;
import java.util.List;

public class HotelRessource {
    private static HotelRessource hotelRessource;
    private static final Services services = new Services();

    private HotelRessource() {}

    public static HotelRessource getInstance() {
        if (hotelRessource == null) {
            hotelRessource = new HotelRessource();
        }
        return hotelRessource;
    }

    public Customer getCustomer(String email) {
        return services.getCustomerService().getCustomer(email);
    }

    public void createACustomer(String email, String firstname, String lastname) {
        services.getCustomerService().addCustomer(email, firstname, lastname);
    }

    public IRoom getRoom(String id) {
        return services.getReservationService().getARoom(id);
    }

    public Reservation bookARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        return services.getReservationService().reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public List<Reservation> getCustomersReservations(String customerEmail) {
        return services.getReservationService().getCustomerReservation(customerEmail);
    }

    public List<IRoom> findARoom(Date checkIn, Date checkOut) {
        return services.getReservationService().findRoom(checkIn, checkOut);
    }
}
