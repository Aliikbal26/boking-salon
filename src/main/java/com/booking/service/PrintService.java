package com.booking.service;

import java.util.List;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
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
    public void showRecentReservation(List<Reservation> reservationsList) {
        int num = 1;
        System.out.println("RESERVASI");
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out
                .println("+========================================================================================+");
        for (Reservation reservation : reservationsList) {
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

    public void showAllCustomer(List<Person> personList) {
        List<Customer> customers = personList.stream()
                .filter(person -> person instanceof Customer)
                .map(person -> (Customer) person)
                .toList();
        int num = 1;
        System.out.println("SHOW CUSTOMER");
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                "No.", "ID", "Nama Customer", "Alamat", "Membership", "Uang");
        System.out
                .println("+====================================================================================+");
        for (Customer customer : customers) {
            System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                    num, customer.getId(), customer.getName(), customer.getAddress(),
                    customer.getMember().getMembershipName(),
                    customer.getWallet());
            num++;
        }
    }

    public void showAllEmployee(List<Person> personList) {
        List<Employee> employees = personList.stream()
                .filter(person -> person instanceof Employee)
                .map(person -> (Employee) person)
                .toList();
        int num = 1;
        System.out.println("SHOW EMPLOYEE");
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s |\n",
                "No.", "ID", "Nama ", "Alamat", "Pengalaman");
        System.out
                .println("+=================================================================+");

        for (Employee employee : employees) {
            System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s |\n",
                    num, employee.getId(), employee.getName(), employee.getAddress(), employee.getExperience());
            num++;
        }
    }

    public void showAllService(List<Service> serviceList) {

        int num = 1;
        System.out.println("SHOW ALL SERVICE");
        System.out.printf("| %-4s | %-4s | %-15s | %-15s |\n",
                "No.", "SERV ID", "Nama Service", "Total Biaya");
        System.out
                .println("+=================================================================+");
        for (Service service : serviceList) {
            System.out.printf("| %-4s | %-4s | %-15s | %-15s |\n",
                    num++, service.getServiceId(), service.getServiceName(), service.getPrice());
        }

    }

    public void showHistoryReservation(List<Reservation> reservationsList) {
        int num = 1;
        System.out.println("SHOW HISTORY RESERVASI");
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-10s\n",
                "No.", "ID", "Nama ", "Service", "Total Biaya", "Workstage");
        System.out
                .println("+=================================================================+");

        for (Reservation reservation : reservationsList) {
            System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s |\n",
                    num, reservation.getReservationId(), reservation.getCustomer(), reservation.getServices(),
                    reservation.getReservationPrice(), reservation.getWorkstage());
            num++;
        }
    }

    public static String formatCurrency(double price) {
        String result = "Rp." + String.format("%,.2f", price);
        return result;
    }
}
