package com.assignment;

import com.assignment.service.ConsoleLoaderService;
import com.assignment.service.GuiLoaderService;

import java.util.Scanner;

/**
 * Application Runner for FormulaChampionshipSimulator
 */
public class Application {

    public static void main(String[] args) {
        //scanner for user input
        Scanner inputScanner = new Scanner(System.in);
        //display user message
        doDisplayMessage();
        //render based on user input
        doRenderUserInterface(inputScanner.nextLine());
    }

    //do display user message
    public static void doDisplayMessage() {
        System.out.println("###### Welcome to Formula Championship Manager ##########"
                + "\n\n\t(*) To proceed Console based, press 'C'"
                + "\n\t(*) To proceed GUI based, press 'G'"
                + "\n\t(*) To exit, press 'E'"
                + "\n\n#############################################################");
    }

    //do render user interface
    public static void doRenderUserInterface(String userInput) {
        if (userInput.equals("C") || userInput.equals("c")) {
            new ConsoleLoaderService().doLoadUserInterface();
        } else if (userInput.equals("G") || userInput.equals("g")) {
            new GuiLoaderService().doLoadUserInterface();
        }
    }
}
