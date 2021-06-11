package service;

public class Services {
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();

    public Services() {}

    public CustomerService getCustomerService() {
        return customerService;
    }

    public ReservationService getReservationService() {
        return reservationService;
    }
}
