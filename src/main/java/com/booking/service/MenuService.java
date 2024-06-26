package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;

public class MenuService {
    private static List<Person> personList = PersonRepository.getAllPerson();
    private static List<Service> serviceList = ServiceRepository.getAllService();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();
    private static List<Reservation> reservationList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void mainMenu() {
        String[] mainMenuArr = { "Show Data", "Create Reservation", "Complete/cancel reservation", "Exit" };
        String[] subMenuArr = { "Recent Reservation", "Show Customer", "Show Available Employee", "Back to main menu" };

        int optionMainMenu;
        int optionSubMenu;

        boolean backToMainMenu = false;
        boolean backToSubMenu = false;
        do {
            PrintService.printMenu("Main Menu", mainMenuArr);
            optionMainMenu = Integer.valueOf(input.nextLine());
            switch (optionMainMenu) {
                case 1:
                    do {
                        PrintService.printMenu("Show Data", subMenuArr);
                        optionSubMenu = Integer.valueOf(input.nextLine());
                        // Sub menu - menu 1
                        switch (optionSubMenu) {
                            case 1:
                                // panggil fitur tampilkan recent reservation
                                PrintService printService = new PrintService();
                                printService.showRecentReservation(reservationList);
                                break;
                            case 2:
                                PrintService customer = new PrintService();
                                customer.showAllCustomer(personList);
                                // panggil fitur tampilkan semua customer
                                break;
                            case 3:
                                // panggil fitur tampilkan semua employee
                                PrintService empleoyee = new PrintService();
                                empleoyee.showAllEmployee(personList);
                                break;
                            case 4:

                                // panggil fitur tampilkan history reservation + total keuntungan
                                break;
                            case 0:
                                backToSubMenu = true;
                        }
                    } while (!backToSubMenu);
                    break;
                case 2:
                    ReservationService.createReservation(reservationList, personList, serviceList, input);
                    // ReservationService.createReservation(reservationList, customers, input);
                    // panggil fitur menambahkan reservation
                    break;
                case 3:
                    // ReservationService.editReservationWorkstage(reservationList, input);
                    // panggil fitur mengubah workstage menjadi finish/cancel
                    break;
                case 0:
                    backToMainMenu = false;
                    break;
            }
        } while (!backToMainMenu);

    }
}
