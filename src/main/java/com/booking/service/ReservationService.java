package com.booking.service;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Optional;
import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;

public class ReservationService {
    // public static void createReservation(List<Reservation> listReservations,
    // List<Service> serviceList,
    // List<Person> personList) {
    // Customer customer = new Customer();
    // Employee employee = new Employee();

    // }

    public static void editReservationWorkstage(List<Reservation> reservationsList, Scanner input) {
        PrintService printService = new PrintService();
        printService.showRecentReservation(reservationsList);

        // String reservationId = new Scanner(input.nextLine());
        System.out.println("Masukkan id reservasi: ");
        String reservationId = input.nextLine();

        // try {
        // Reservation updateReservasi = findServiceById(reservationsList, String
        // reservationId);
        // } catch (Exception e) {
        // // TODO: handle exception
        // }

    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan

    public static void createReservation(List<Reservation> reservationsList, List<Person> personList,
            List<Service> serviceList, Scanner input) {
        Customer customer = getValidCustomer(personList, input);
        Employee employee = getValidEmployee(personList, input);
        List<Service> orderedServices = getOrderedServices(serviceList, input);

        Reservation newReservation = new Reservation(generateReservationId(reservationsList), customer, employee,
                orderedServices, "in process");
        reservationsList.add(newReservation);

        System.out.println("Booking berhasil!");
        System.out.println("Total biaya booking: " + PrintService.formatCurrency(newReservation.getReservationPrice()));
    }

    private static Customer getValidCustomer(List<Person> personList, Scanner input) {
        PrintService printService = new PrintService();
        printService.showAllCustomer(personList);
        while (true) {
            System.out.println("Masukkan customer id di bawah ini: ");
            String customerId = input.nextLine();
            Optional<Customer> customerOptional = findCustomerById(personList, customerId);
            if (customerOptional.isPresent()) {
                return customerOptional.get();
            } else {
                System.out.println("Data customer tidak ditemukan! Silakan masukkan kembali id customer yang benar");
            }
        }
    }

    private static Optional<Customer> findCustomerById(List<Person> personList, String customerId) {
        return personList.stream()
                .filter(person -> person instanceof Customer)
                .map(person -> (Customer) person)
                .filter(customer -> customer.getId().equalsIgnoreCase(customerId))
                .findFirst();
    }

    // private static findReservationId(List<Reservation> reservationsList, String
    // reservationId) {
    // return reservationsList.stream()
    // .filter(reservation ->
    // reservation.getReservationId().equalsIgnoreCase(reservationId))
    // .findFirst();
    // }

    private static Employee getValidEmployee(List<Person> personList, Scanner input) {
        PrintService printService = new PrintService();
        printService.showAllEmployee(personList);

        while (true) {
            System.out.println("Masukkan id pegawai di bawah ini: ");
            String employeeId = input.nextLine();
            Optional<Employee> employeeOptional = findEmployeeById(personList, employeeId);
            if (employeeOptional.isPresent()) {
                return employeeOptional.get();
            } else {
                System.out.println("Data pegawai tidak ditemukan! Silakan masukkan kembali id pegawai yang benar");
            }
        }
    }

    private static Optional<Employee> findEmployeeById(List<Person> personList, String employeeId) {
        return personList.stream()
                .filter(person -> person instanceof Employee)
                .map(person -> (Employee) person)
                .filter(employee -> employee.getId().equalsIgnoreCase(employeeId))
                .findFirst();
    }

    private static List<Service> getOrderedServices(List<Service> serviceList, Scanner input) {
        List<Service> orderedServices = new ArrayList<>();

        PrintService printService = new PrintService();
        printService.showAllService(serviceList);
        while (true) {
            System.out.println("Masukkan service id di bawah ini: ");
            String serviceId = input.nextLine();
            Optional<Service> serviceOptional = findServiceById(serviceList, serviceId);
            if (serviceOptional.isPresent() && !checkAddedService(orderedServices, serviceId)) {
                orderedServices.add(serviceOptional.get());
                System.out.println("Ingin pilih service yang lain? (Y/N)");
                String userInput = ValidationService.getValidatedStringInput(input, "\\b[YyNn]\\b",
                        "Hanya bisa menerima input berupa huruf y dan n (kapital atau kecil)");
                if (userInput.equalsIgnoreCase("n")) {
                    break;
                }
            } else {
                System.out.println(
                        "Service tidak ditemukan atau sudah ditambahkan! Silakan masukkan kembali id service yang benar");
            }
        }
        return orderedServices;
    }

    private static Optional<Service> findServiceById(List<Service> serviceList, String serviceId) {
        return serviceList.stream()
                .filter(service -> service.getServiceId().equalsIgnoreCase(serviceId))
                .findFirst();
    }

    private static String generateReservationId(List<Reservation> reservationsList) {
        String result = "Rsv-";
        int newNumber = reservationsList.isEmpty() ? 1
                : Integer.parseInt(reservationsList.get(reservationsList.size() - 1).getReservationId().substring(4))
                        + 1;
        return result + newNumber;
    }

    private static boolean checkAddedService(List<Service> orderedServices, String serviceId) {
        return orderedServices.stream()
                .anyMatch(service -> service.getServiceId().equalsIgnoreCase(serviceId));
    }

}
