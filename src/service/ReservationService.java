package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationService {
    private static ReservationService reservationService;
    private static final List<IRoom> rooms = new ArrayList<>();
    private static final List<Reservation> reservations = new ArrayList<>();

    private ReservationService(){}

    public static ReservationService getInstance() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public IRoom getARoom(String roomId) {
        IRoom r = null;

        for (IRoom room : rooms) {
            if (room.getRoomId().equals(roomId)) {
                r = room;
            }
        }
        return r;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public List<IRoom> findRoom(Date checkIn, Date checkOut) {
        List<IRoom> roomList = rooms;

       if (!reservations.isEmpty()){
            for (Reservation r : reservations) {
                if (!((checkIn.before(r.getCheckInDate()) && checkOut.before(r.getCheckInDate())) ||
                        (checkIn.after(r.getCheckOutDate()) && checkOut.after(r.getCheckOutDate())))) {
                    roomList.remove(r.getRoom());
                }
            }
        }

       return roomList;
    }

    public List<Reservation> getCustomerReservation(Customer customer) {
        List<Reservation> reservationList = new ArrayList<>();

        for (Reservation r : reservations) {
            if (r.customer.equals(customer)) {
                reservationList.add(r);
            }
        }
        return reservationList;
    }

    public List<Reservation> getCustomerReservation(String customerEmail) {
        List<Reservation> reservationList = new ArrayList<>();

        for (Reservation r : reservations) {
            if (r.customer.getEmail().equals(customerEmail)) {
                reservationList.add(r);
            }
        }
        return reservationList;
    }

    public void printAllReservations() {
        for (Reservation r : reservations) {
            System.out.println(r.getCustomer().getEmail() + ": " + r.getRoom().getRoomId() + "from " +r.getCheckInDate()
            + "to " + r.getCheckOutDate());
        }
    }

    public List<IRoom> getRooms() {
        return rooms;
    }
}
