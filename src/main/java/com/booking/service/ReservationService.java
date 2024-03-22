package com.booking.service;

import java.util.List;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ReservationService {
    public static void createReservation() {
    }

    public static Customer getCustomerByCustomerId(List<Customer> listCustomers, String customerId) {
        return listCustomers.stream()
                .filter(customer -> customer.getId().equalsIgnoreCase(customerId))
                .findFirst()
                .orElseThrow();
    }

    public static void editReservationWorkstage() {

    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
