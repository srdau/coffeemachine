package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int bank = 550;
    private int waterReservoir = 400;
    private int milkReservoir = 540;
    private int coffeeBeansSupply = 120;
    private int cupSupply = 9;
    private CoffeeMachineStatus coffeeMachineStatus = CoffeeMachineStatus.STOPPED;

    private void displayMachineStock() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(waterReservoir + " of water");
        System.out.println(milkReservoir + " of milk");
        System.out.println(coffeeBeansSupply + " of coffee beans");
        System.out.println(cupSupply + " of disposable cups");
        System.out.println(bank + " of money");
    }

    private void fillWater(String inputLine) {
        waterReservoir += Integer.parseInt(inputLine);
    }

    private void fillMilk(String inputLine) {
        milkReservoir += Integer.parseInt(inputLine);
    }

    private void fillBeans(String inputLine) {
        coffeeBeansSupply += Integer.parseInt(inputLine);
    }

    private void fillCups(String inputLine) {
        cupSupply += Integer.parseInt(inputLine);
    }

    private void makeCoffee(int water, int milk, int coffeeBeans, int price) {
        coffeeMachineStatus = CoffeeMachineStatus.CHECKING_STOCK;

        if (water > waterReservoir) {
            System.out.println("Sorry, not enough water!\n");
        } else if (milk > milkReservoir) {
            System.out.println("Sorry, not enough milk!\n");
        } else if (coffeeBeans > coffeeBeansSupply) {
            System.out.println("Sorry, not enough coffee beans!\n");
        } else if (cupSupply < 1) {
            System.out.println("Sorry, not enough disposable cups!\n");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            coffeeMachineStatus = CoffeeMachineStatus.MAKING_COFFEE;

            waterReservoir -= water;
            milkReservoir -= milk;
            coffeeBeansSupply -= coffeeBeans;
            cupSupply--;
            bank += price;

            coffeeMachineStatus = CoffeeMachineStatus.COFFEE_MADE;
            System.out.println();
        }
    }

    private void makeEspresso() {
        makeCoffee(250, 0, 16, 4);
    }

    private void makeLatte() {
        makeCoffee(350, 75, 20, 7);
    }

    private void makeCappuccino() {
        makeCoffee(200, 100, 12, 6);
    }

    private void buyMenu(String buyOption) {
        switch (buyOption) {
            case "1":
                makeEspresso();
                coffeeMachineStatus = CoffeeMachineStatus.MAIN_MENU;
                break;

            case "2":
                makeLatte();
                coffeeMachineStatus = CoffeeMachineStatus.MAIN_MENU;
                break;

            case "3":
                makeCappuccino();
                coffeeMachineStatus = CoffeeMachineStatus.MAIN_MENU;
                break;

            case "back":
                coffeeMachineStatus = CoffeeMachineStatus.MAIN_MENU;
                System.out.println();
                break;

            default:
                System.out.println("I don't know how to make that.\n");
                coffeeMachineStatus = CoffeeMachineStatus.MAIN_MENU;
                break;
        }
    }

    private void takeMoney() {
        System.out.println("\nI gave you $" + bank + "\n");
        bank = 0;
    }

    private void mainMenu(String option) {
        switch (option) {
            case "buy":
                coffeeMachineStatus = CoffeeMachineStatus.BUY_MENU;
                break;

            case "fill":
                coffeeMachineStatus = CoffeeMachineStatus.BEGIN_FILL;
                coffeeMachineStatus = CoffeeMachineStatus.FILL_WATER;
                break;

            case "take":
                coffeeMachineStatus = CoffeeMachineStatus.TAKING;
                takeMoney();
                coffeeMachineStatus = CoffeeMachineStatus.MAIN_MENU;
                break;

            case "remaining":
                coffeeMachineStatus = CoffeeMachineStatus.REPORTING;
                displayMachineStock();
                coffeeMachineStatus = CoffeeMachineStatus.MAIN_MENU;
                System.out.println();
                break;

            case "exit":
                coffeeMachineStatus = CoffeeMachineStatus.STOPPING;
                break;

            default:
                System.out.println("Unknown option");
                break;
        }
    }

    private void processHumanInput(String inputLine) {
        switch (coffeeMachineStatus) {
            case MAIN_MENU:
                mainMenu(inputLine);
                break;

            case BUY_MENU:
                buyMenu(inputLine);
                break;

            case FILL_WATER:
                fillWater(inputLine);
                coffeeMachineStatus = CoffeeMachineStatus.FILL_MILK;
                break;

            case FILL_MILK:
                fillMilk(inputLine);
                coffeeMachineStatus = CoffeeMachineStatus.FILL_BEANS;
                break;

            case FILL_BEANS:
                fillBeans(inputLine);
                coffeeMachineStatus = CoffeeMachineStatus.FILL_CUPS;
                break;

            case FILL_CUPS:
                fillCups(inputLine);
                coffeeMachineStatus = CoffeeMachineStatus.MAIN_MENU;
                System.out.println();
                break;

            default:
                System.out.println("I cannot process input while in this state.");
                coffeeMachineStatus = CoffeeMachineStatus.STOPPING;
                break;
        }

    }

    private void displayPrompt() {
        switch (coffeeMachineStatus) {
            case MAIN_MENU:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                System.out.print("> ");
                break;

            case BUY_MENU:
                System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                System.out.print("> ");
                break;

            case FILL_WATER:
                System.out.println("Write how many ml of water do you want to add:");
                System.out.print("> ");
                break;

            case FILL_MILK:
                System.out.println("Write how many ml of milk do you want to add:");
                System.out.print("> ");
                break;

            case FILL_BEANS:
                System.out.println("Write how many grams of coffee beans do you want to add:");
                System.out.print("> ");
                break;

            case FILL_CUPS:
                System.out.println("Write how many disposable cups of coffee dp ypu want to add:");
                System.out.print("> ");
                break;

            default:
                System.out.println("Error - no valid prompt for this machine state.");
                break;
        }
    }

    private String getHumanInput() {
        Scanner humanInput = new Scanner(System.in);

        displayPrompt();
        return humanInput.nextLine();
    }

    public static void main(String[] args) {
        CoffeeMachine ourMachine = new CoffeeMachine();

        ourMachine.coffeeMachineStatus = CoffeeMachineStatus.INITIALISING;
        ourMachine.coffeeMachineStatus = CoffeeMachineStatus.STARTED;
        ourMachine.coffeeMachineStatus = CoffeeMachineStatus.MAIN_MENU;

        while (ourMachine.coffeeMachineStatus != CoffeeMachineStatus.STOPPING) {
            ourMachine.processHumanInput(ourMachine.getHumanInput());
        }
    }
}
