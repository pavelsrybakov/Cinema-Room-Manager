package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeats = scanner.nextInt();
        char[][] seats = new char[numRows][numSeats];
        for (char[] row: seats) {
            Arrays.fill(row, 'S');
        }

        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            switch (choice){
                case 1: {
                    printArrayLayout(seats);
                    break;
                }
                case 2: {
                    buyTicket(seats);
                    break;
                }
                case 3: {
                    showStatistics(seats);
                    break;
                }
                case 0: {
                    return;
                }
                default: {
                    System.out.println("Wrong input");
                }
            }
        }
    }

    public static void showStatistics(char[][] seats) {
        int numRows = seats.length;
        int numSeats = seats[0].length;
        int totalTickets = numRows * numSeats;
        int soldTickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;
        for (int row = 0; row < numRows; row++) {
            for (int seat = 0; seat < numSeats; seat++) {
                int ticketPrice = calculatePrice(row + 1, numRows, numSeats);
                if (seats[row][seat] == 'B') {
                    soldTickets += 1;
                    currentIncome += ticketPrice;
                }
                totalIncome += ticketPrice;
            }
        }
        String percentage = String.format("%.2f", (float) soldTickets / (float) totalTickets * 100);
        System.out.println("Number of purchased tickets: " + soldTickets);
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static int calculatePrice(int row, int numRows, int numSeats) {
        final int seatsBorder = 60;
        final int highPrice = 10;
        final int lowPrice = 8;
        int totalSeats = numRows * numSeats;
        return totalSeats <= seatsBorder ? highPrice : row > numRows / 2 ? lowPrice : highPrice;

    }

    public static void printArrayLayout(char[][] seats) {

        // Getting room parameters
        int numRows = seats.length;
        int numSeats = seats[0].length;
        // Printing first row of room layout
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 0; i < numSeats; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        // Printing room layout
        for (int i = 0; i < numRows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < numSeats; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void buyTicket(char[][] seats) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();
            if (row > seats.length || seat > seats.length || row < 1 || seat < 1) {
                System.out.println("Wrong input!");
                continue;
            }
            if (seats[row-1][seat-1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            System.out.println("Ticket price: $" + calculatePrice(row, seats.length, seats[0].length));
            seats[row-1][seat-1] = 'B';
            return;
        }
    }

}

