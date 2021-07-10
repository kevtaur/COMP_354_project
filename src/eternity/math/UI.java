package eternity.math;

import com.expression.parser.Parser;
import com.expression.parser.exception.CalculatorException;
import eternity.exception.EmptyInputException;
import eternity.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UI {
    private static List<String> algebraHistory = new ArrayList<>();
    private static List<String> financeHistory = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ETERNITY!");
        menuLoop:
        while(true){
            System.out.println("Let us know what you want to do: ");
            System.out.println("List");
            String menuInput = scanner.nextLine();
            menuInput = menuInput.replaceAll("\\s","");
            try {
                if (menuInput.equals("") || menuInput == null)
                    throw new EmptyInputException("Empty input detected. Please re-enter your input and hit enter.");
                else if (menuInput.equals("1")) {
                    //start algebra calc
                    algebraLoop:
                    while (true) {
                        try {
                            System.out.println("Please enter your equation and hit enter: \n" +
                                    "Enter m to go back to main menu \n" +
                                    "Enter h to see your history \n" +
                                    "Enter q to exit the program");
                            String algebraInput = scanner.nextLine();
                            algebraInput = algebraInput.replaceAll("\\s","");
                            if (algebraInput.equals("") || algebraInput == null) {
                                throw new EmptyInputException("Empty input detected.");
                            } else if (algebraInput.equalsIgnoreCase("m")) {
                                break algebraLoop;
                            } else if (algebraInput.equalsIgnoreCase("q")) {
                                System.out.println("Thank you for using ETERNITY.");
                                algebraHistory.clear();
                                financeHistory.clear();
                                System.exit(0);
                            } else if (algebraInput.equalsIgnoreCase("h")) {
                                System.out.println("Here is the history of your previous equations: ");
                                for (String s : algebraHistory) {
                                    System.out.println(s);
                                }
                                System.out.println();
                            } else {
                                try {
                                    Double answer = Parser.eval(algebraInput).getValue();
                                    String result = algebraInput + " = " + answer;
                                    System.out.println(result);
                                    algebraHistory.add(result);
                                } catch (CalculatorException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                        catch (EmptyInputException e){
                            System.out.println(e.getMessage());
                        }
                    }

                } else if (menuInput.equals("2")) {
                    // start finance calc
                    financeLoop:
                    while(true) {
                        try {
                            System.out.println("Please select the function that you want:  \n" +
                                    "Enter 1 for Mean Absolute Deviation \n" +
                                    "Enter 2 for Standard Deviation \n" +
                                    "Enter m to go back to main menu \n" +
                                    "Enter h to see your history \n" +
                                    "Enter q to exit the program");
                            String financeInput = scanner.nextLine();
                            financeInput = financeInput.replaceAll("\\s","");
                            if (financeInput.equals("") || financeInput == null) {
                                throw new EmptyInputException("Empty input detected.");
                            } else if (financeInput.equalsIgnoreCase("m")) {
                                break financeLoop;
                            } else if (financeInput.equalsIgnoreCase("q")) {
                                System.out.println("Thank you for using ETERNITY.");
                                algebraHistory.clear();
                                financeHistory.clear();
                                System.exit(0);
                            } else if (financeInput.equalsIgnoreCase("h")) {
                                System.out.println("Here is the history of your previous equations: ");
                                for (String s : financeHistory) {
                                    System.out.println(s);
                                }
                                System.out.println();
                            } else if (financeInput.equalsIgnoreCase("1")){
                                // start MAD
                                MADLoop:
                                while(true){
                                    try{
                                        System.out.println("Please enter the series of your inputs separated by a space, and hit Enter when done: \n"+
                                                "Enter m to go back to the previous menu \n" +
                                                "Enter h to see your history \n" +
                                                "Enter q to exit the program");
                                        String MADinput = scanner.nextLine();
                                        if (MADinput.equalsIgnoreCase("") || MADinput == null){
                                            throw new EmptyInputException("Empty input detected");
                                        } else if (MADinput.equalsIgnoreCase("m")) {
                                            break MADLoop;
                                        } else if (MADinput.equalsIgnoreCase("q")) {
                                            System.out.println("Thank you for using ETERNITY.");
                                            algebraHistory.clear();
                                            financeHistory.clear();
                                            System.exit(0);
                                        } else if (MADinput.equalsIgnoreCase("h")) {
                                            System.out.println("Here is the history of your previous equations: ");
                                            for (String s : financeHistory) {
                                                System.out.println(s);
                                            }
                                            System.out.println();
                                        }
                                        else if (MADinput.matches("[0-9\\s.]+")){
                                            List<String> listOfStringInputs = new ArrayList<>();
                                            listOfStringInputs = Arrays.asList(MADinput.split("\\s"));
                                            ArrayList<Double> listOfDoubleInputs = new ArrayList<>();
                                            listOfStringInputs.forEach(string -> listOfDoubleInputs.add(Double.valueOf(string)));
                                            Double answer = Functions.meanAbsoluteDeviation(listOfDoubleInputs);
                                            String result = "Mean Absolute Deviation of (";
                                            for (int i=0; i<listOfStringInputs.size(); i++){
                                                result += listOfStringInputs.get(i);
                                                if (i != listOfStringInputs.size()-1){
                                                    result += ", ";
                                                }
                                                else {
                                                    result += ")";
                                                }
                                            }
                                            result += " = " + answer;
                                            financeHistory.add(result);
                                            System.out.println(result);
                                        } else
                                            throw new InvalidInputException("Invalid input");
                                    }
                                    catch (EmptyInputException e){
                                        System.out.println(e.getMessage());
                                    }
                                    catch (InvalidInputException e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                            } else if (financeInput.equalsIgnoreCase("2")){
                                // start STD
                                STDLoop:
                                while(true){
                                    try{
                                        System.out.println("Please enter the series of your inputs separated by a space, and hit Enter when done: \n"+
                                                "Enter m to go back to the previous menu \n" +
                                                "Enter h to see your history \n" +
                                                "Enter q to exit the program");
                                        String STDinput = scanner.nextLine();
                                        if (STDinput.equalsIgnoreCase("") || STDinput == null){
                                            throw new EmptyInputException("Empty input detected");
                                        } else if (STDinput.equalsIgnoreCase("m")) {
                                            break STDLoop;
                                        } else if (STDinput.equalsIgnoreCase("q")) {
                                            System.out.println("Thank you for using ETERNITY.");
                                            algebraHistory.clear();
                                            financeHistory.clear();
                                            System.exit(0);
                                        } else if (STDinput.equalsIgnoreCase("h")) {
                                            System.out.println("Here is the history of your previous equations: ");
                                            for (String s : financeHistory) {
                                                System.out.println(s);
                                            }
                                            System.out.println();
                                        }
                                        else if (STDinput.matches("[0-9\\s.]+")){
                                            List<String> listOfStringInputs = new ArrayList<>();
                                            listOfStringInputs = Arrays.asList(STDinput.split("\\s"));
                                            ArrayList<Double> listOfDoubleInputs = new ArrayList<>();
                                            listOfStringInputs.forEach(string -> listOfDoubleInputs.add(Double.valueOf(string)));
                                            Double answer = Functions.std_dev(listOfDoubleInputs);
                                            String result = "Standard Deviation of (";
                                            for (int i=0; i<listOfStringInputs.size(); i++){
                                                result += listOfStringInputs.get(i);
                                                if (i != listOfStringInputs.size()-1){
                                                    result += ", ";
                                                }
                                                else {
                                                    result += ")";
                                                }
                                            }
                                            result += " = " + answer;
                                            financeHistory.add(result);
                                            System.out.println(result);
                                        } else
                                            throw new InvalidInputException("Invalid input");
                                    }
                                    catch (EmptyInputException e){
                                        System.out.println(e.getMessage());
                                    }
                                    catch (InvalidInputException e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }
                            else {
                                try{
                                    throw new InvalidInputException("Empty input detected.");
                                }catch (InvalidInputException e){
                                    System.out.println(e.getMessage());}
                            }
                        }
                        catch (EmptyInputException e){
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (menuInput.equals("3")) {
                    System.out.println("Thank you for using ETERNITY.");
                    algebraHistory.clear();
                    financeHistory.clear();
                    System.exit(0);
                }
            }
            catch (EmptyInputException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
