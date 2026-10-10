package com.finserve.app;

import java.util.Scanner;

public class FinServeApplication {
    public static void main(String[] args) {
        DemoRunner demo = new DemoRunner();

        if (args.length > 0 && "--demo".equalsIgnoreCase(args[0])) {
            printBanner();
            demo.runAll();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        printBanner();

        while (true) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> demo.showBankingData();
                case "2" -> demo.runStringAlgorithms();
                case "3" -> demo.runSuffixAlgorithms();
                case "4" -> demo.runDynamicProgramming();
                case "5" -> demo.runNetworkFlow();
                case "6" -> demo.runNPAlgorithms();
                case "7" -> demo.runRandomizedParallel();
                case "8" -> demo.runAll();
                case "0" -> {
                    System.out.println("Exiting FinServe. Thank you.");
                    return;
                }
                default -> System.out.println("Invalid option. Please enter 0-8.");
            }
            System.out.println("\nPress ENTER to return to the main menu...");
            scanner.nextLine();
        }
    }

    private static void printBanner() {
        System.out.println("============================================================");
        System.out.println("        FinServe - Intelligent Banking Analytics Platform");
        System.out.println("        Advanced DSA Demonstration Project | Java 17");
        System.out.println("============================================================");
    }

    private static void printMenu() {
        System.out.println("\n--------------------------- MENU ---------------------------");
        System.out.println("1. Banking Data Overview");
        System.out.println("2. M1-CO1  String Algorithms");
        System.out.println("3. M2-CO2  Suffix Structures");
        System.out.println("4. M3-CO3  Advanced Dynamic Programming");
        System.out.println("5. M4-CO4  Network Flow");
        System.out.println("6. M5-CO5  NP-Completeness & Approximation");
        System.out.println("7. M6-CO6  Randomized & Parallel Algorithms");
        System.out.println("8. Run Complete FinServe Demo");
        System.out.println("0. Exit");
        System.out.println("------------------------------------------------------------");
    }
}
