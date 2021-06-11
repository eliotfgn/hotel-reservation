package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerService {
    private static CustomerService customerService;
    private static final ArrayList<Customer> customers = new ArrayList<>();

    private CustomerService(){}

    public static CustomerService getInstance(){
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String email, String firstname, String lastname) {
        customers.add(new Customer(firstname, lastname, email));
    }

    public Customer getCustomer(String email) {
        Customer cus = null;
        for (Customer customer:
             customers) {
            if (customer.getEmail().equals(email)) {
                cus = customer;
            }
        }
        return cus;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }
}
