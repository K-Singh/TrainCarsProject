package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The driver class, it allows users to do various operations on the Train. Users can add or remove cars from
 * the Linked List. They can also move the cursor around and do other operations such as searching for and setting
 * products, displaying the train and train manifest, and removing all dangerous cars from the train.
 *
 * @author Kirat Singh
 */
public class TrainManager {
    /**
     * This main menu drives the application. Here users can input various operations that allows the user to edit
     * the train. Users will also be asked for additional information upon putting an option in so that the train can
     * be properly modified.
     */
    public static void main(String[] args) {
        TrainLinkedList t = new TrainLinkedList();
        String operation = "";
        String options = "(F) Cursor Forward \n" +
                "(B) Cursor Backward \n" +
                "(I) Insert Car After Cursor \n" +
                "(R) Remove Car At Cursor \n" +
                "(L) Set Product Load \n" +
                "(S) Search For Product \n" +
                "(T) Display Train \n" +
                "(M) Display Manifest \n" +
                "(D) Remove Dangerous Cars \n" +
                "(Q) Quit";
        Scanner menu = new Scanner(System.in);
        while(!operation.equalsIgnoreCase("q")){
            System.out.print("\n"+options + "\n");
            System.out.print("Enter a selection: ");
            operation = menu.nextLine();
            double carLength = 0;
            double carWeight = 0;
            String loadName = "EMPTY";
            double loadWeight = 0;
            double loadValue = 0;
            boolean loadDangerous = false;
            TrainCar tc = null;
            ProductLoad load;
            switch(operation.toLowerCase()) {
                case "q":
                    System.out.println("The program has been terminated.");
                    break;
                case "i":
                    try {
                        System.out.print("Enter car length in meters: ");
                        carLength = menu.nextDouble();
                        menu.nextLine();
                        System.out.print("Enter car weight in tons: ");
                        carWeight = menu.nextDouble();
                        menu.nextLine();

                        tc = new TrainCar(carLength, carWeight);
                        t.insertAfterCursor(tc);
                        System.out.println("Train car with a length of: "+carLength+" meters and a weight of: "
                                + carWeight + " tons" + " has been inserted into the train");
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }catch (InputMismatchException e){
                        System.out.println("Please put a valid input for the weight/length!");
                        menu.nextLine();
                    }
                    break;
                case "m":
                    t.printManifest();
                    break;
                case "f":
                    t.cursorForward();
                    break;
                case "b":
                    t.cursorBackward();
                    break;
                case "r":
                    try {
                        t.removeCursor();
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "l":
                    try {
                        System.out.print("Enter the product name: ");
                        loadName = menu.nextLine();
                        System.out.print("Enter the product weight: ");
                        loadWeight = menu.nextDouble();
                        menu.nextLine();
                        System.out.print("Enter the product value: ");
                        loadValue = menu.nextDouble();
                        menu.nextLine();
                        System.out.print("Is the product dangerous(y/n)");
                        String dangerousValue;
                        dangerousValue = menu.nextLine();
                        if (dangerousValue.equalsIgnoreCase("y"))
                            loadDangerous = true;
                        else if (dangerousValue.equalsIgnoreCase("n"))
                            loadDangerous = false;
                        else {
                            System.out.println("That option is not valid!(Must be y/n)");
                            break;
                        }
                        load = new ProductLoad(loadName, loadWeight, loadValue, loadDangerous);
                        t.getCursorData().setCarLoad(load);
                        t.setValue(t.getValue() + loadValue);
                        t.setWeight(t.getWeight() + loadWeight);
                        if (loadDangerous) {
                            t.setDangerous(true);
                            t.setNumDangerousCars(t.getNumDangerousCars() + 1);
                        }
                        System.out.println("A load of " + String.format(("%.1f"), loadWeight) + " " +
                                "tons added to current car!");
                    }catch(InputMismatchException e){
                        System.out.println("Please enter valid inputs!");
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "t":
                    System.out.println(t.toString());
                    break;
                case "d":
                    t.removeDangerousCars();
                    break;
                case "s":
                    System.out.print("Please enter the name of the load(s) you are trying to find: ");
                    loadName = menu.nextLine();
                    try{
                        t.findProduct(loadName);
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("That option does not exist!");
                    break;
            }
        }
    }
}
