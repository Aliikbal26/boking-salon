package com.booking.service;

import java.util.List;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class PrintService {
    public static void printMenu(String title, String[] menuArr) {
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);
            num++;
        }
    }

    public String printServices(List<Service> serviceList) {
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public void showRecentReservation(List<Reservation> reservationList) {
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out
                .println("+========================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting")
                    || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                        num, reservation.getReservationId(), reservation.getCustomer().getName(),
                        printServices(reservation.getServices()), reservation.getReservationPrice(),
                        reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
    }

    public void showAllCustomer(List<Customer> customerList) {
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                "No.", "ID", "Nama Customer", "Alamat", "Membership", "Uang");
        System.out
                .println("+====================================================================================+");
        for (Customer customer : customerList) {
            System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                    num, customer.getId(), customer.getName(), customer.getAddress(), customer.getMember(),
                    customer.getWallet());
            num++;
        }
    }

    public void showAllEmployee(List<Employee> employees) {
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s |\n",
                "No.", "ID", "Nama ", "Alamat", "Pengalaman");
        System.out
                .println("+=================================================================+");

        for (Employee employee : employees) {
            System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                    num, employee.getId(), employee.getName(), employee.getAddress(), employee.getExperience());
            num++;
        }
    }

    public void showHistoryReservation(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            System.out.println(Reservation.class);
        }
    }
}
