package org.example;

import java.util.Scanner;

public class  Application {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        Scanner scanner = new Scanner(System.in);
        simulation.initSimulation();
        String symbol = scanner.nextLine();
        if (symbol != null) {
            simulation.stopSimulation();
        }
        scanner.close();
    }
}