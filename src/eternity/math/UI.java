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
    private static List<String> history = new ArrayList<>();
//    private static List<String> financeHistory = new ArrayList<>();
    private static Decimal decimal = new Decimal();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ETERNITY!");
        menuLoop:
        while(true) {
        	System.out.println("\nMain Menu");
            System.out.println(" - 1 for Algebra calculator \n" +
			                   " - 2 for Finance calculator (Mean Absolute Deviation and Standard Deviation) \n" +
                                " - 3 for Settings \n" +
			                   " - q to exit the program");
            System.out.print("Let us know what you want to do: ");
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
                        	System.out.println("\nAlgebra Calculator");
                            System.out.println(" - m to go back to main menu \n" +
			                                   " - h to see your history \n" +
			                                   " - q to exit the program");
                            System.out.print("Please enter your equation and hit enter: ");
                            String algebraInput = scanner.nextLine();
                            algebraInput = algebraInput.replaceAll("\\s","");
                            if (algebraInput.equals("") || algebraInput == null) {
                                throw new EmptyInputException();
                            } else if (algebraInput.equalsIgnoreCase("m")) {
                                break algebraLoop;
                            } else if (algebraInput.equalsIgnoreCase("q")) {
                                System.out.println("Thank you for using ETERNITY.");
                                history.clear();
                                System.exit(0);
                            } else if (algebraInput.equalsIgnoreCase("h")) {
                                System.out.println("Here is the history of your previous equations: ");
                                for (String s : history) {
                                    System.out.println(s);
                                }
                                System.out.println();
                            } else {
                                try {
                                    Double rawAnswer = Parser.eval(algebraInput).getValue();
                                    Double answerWithDecimal = decimal.getResultDecimal(rawAnswer);
                                    String result = algebraInput + " = " + answerWithDecimal;
                                    System.out.println(result);
                                    history.add(result);
                                } catch (CalculatorException e) {
                                    System.out.println(e.getMessage());
                                } catch (NumberFormatException e){
                                    System.out.println("MATH ERROR");
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
                        	System.out.println("\nFinance Calculator");
                            System.out.println(" - 1 for Mean Absolute Deviation \n" +
			                                   " - 2 for Standard Deviation \n" +
			                                   " - m to go back to main menu \n" +
			                                   " - h to see your history \n" +
			                                   " - q to exit the program");
                            System.out.print("Please select the function that you want: ");
                            String financeInput = scanner.nextLine();
                            financeInput = financeInput.replaceAll("\\s","");
                            if (financeInput.equals("") || financeInput == null) {
                                throw new EmptyInputException();
                            } else if (financeInput.equalsIgnoreCase("m")) {
                                break financeLoop;
                            } else if (financeInput.equalsIgnoreCase("q")) {
                                System.out.println("Thank you for using ETERNITY.");
                                history.clear();
                                System.exit(0);
                            } else if (financeInput.equalsIgnoreCase("h")) {
                                System.out.println("Here is the history of your previous equations: ");
                                for (String s : history) {
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
                                            throw new EmptyInputException();
                                        } else if (MADinput.equalsIgnoreCase("m")) {
                                            break MADLoop;
                                        } else if (MADinput.equalsIgnoreCase("q")) {
                                            System.out.println("Thank you for using ETERNITY.");
                                            history.clear();
                                            System.exit(0);
                                        } else if (MADinput.equalsIgnoreCase("h")) {
                                            System.out.println("Here is the history of your previous equations: ");
                                            for (String s : history) {
                                                System.out.println(s);
                                            }
                                            System.out.println();
                                        }
                                        else if (MADinput.matches("[0-9\\s.]+")){
                                            List<String> listOfStringInputs = new ArrayList<>();
                                            listOfStringInputs = Arrays.asList(MADinput.split("\\s"));
                                            ArrayList<Double> listOfDoubleInputs = new ArrayList<>();
                                            listOfStringInputs.forEach(string -> listOfDoubleInputs.add(Double.valueOf(string)));
                                            Double rawAnswer = Functions.meanAbsoluteDeviation(listOfDoubleInputs);
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
                                            Double answerWithDecimal = decimal.getResultDecimal(rawAnswer);
                                            result += " = " + answerWithDecimal;
                                            history.add(result);
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
                                                "\tm to go back to the previous menu \n" +
                                                "\th to see your history \n" +
                                                "\tq to exit the program");
                                        String STDinput = scanner.nextLine();
                                        if (STDinput.equalsIgnoreCase("") || STDinput == null){
                                            throw new EmptyInputException();
                                        } else if (STDinput.equalsIgnoreCase("m")) {
                                            break STDLoop;
                                        } else if (STDinput.equalsIgnoreCase("q")) {
                                            System.out.println("Thank you for using ETERNITY.");
                                            history.clear();
                                            System.exit(0);
                                        } else if (STDinput.equalsIgnoreCase("h")) {
                                            System.out.println("Here is the history of your previous equations: ");
                                            for (String s : history) {
                                                System.out.println(s);
                                            }
                                            System.out.println();
                                        }
                                        else if (STDinput.matches("[0-9\\s.]+")) {
                                            List<String> listOfStringInputs = new ArrayList<>();
                                            listOfStringInputs = Arrays.asList(STDinput.split("\\s"));
                                            ArrayList<Double> listOfDoubleInputs = new ArrayList<>();
                                            listOfStringInputs.forEach(string -> listOfDoubleInputs.add(Double.valueOf(string)));
                                            Double rawAnswer = Functions.std_dev(listOfDoubleInputs);
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
                                            Double answerWithDecimal = decimal.getResultDecimal(rawAnswer);
                                            result += " = " + answerWithDecimal;
                                            history.add(result);
                                            System.out.println(result);
                                        } else
                                            throw new InvalidInputException("Invalid input");
                                    }
                                    catch (EmptyInputException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    catch (InvalidInputException e){
                                        System.out.println(e.getMessage());
                                    }
                                    catch (CalculatorException e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }
                            else {
                                try{
                                    throw new InvalidInputException("Empty input detected.");
                                }catch (InvalidInputException e) {
                                    System.out.println(e.getMessage());}
                            }
                        }
                        catch (EmptyInputException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (menuInput.equalsIgnoreCase("3")) {
                    settingLoop:
                    while(true){
                        System.out.println("\nSettings");
                        System.out.println(" - 1 for Decimals \n" +
                                " - 2 for Degree/Radian");
                        System.out.println("Please select your option: ");
                        String settingInput = scanner.nextLine();

                        if (settingInput.equalsIgnoreCase("") || settingInput == null){
                            throw new EmptyInputException();
                        } else if (settingInput.equalsIgnoreCase("1")) {
                            decimalLoop:
                            while(true){
                                System.out.println("\nDecimals");
                                System.out.println("Please enter the number of decimal places you would like your result to have: ");
                                String decimalStringInput = scanner.nextLine();
                                int decimalIntInput =0;
                                try {
                                    decimalIntInput = Integer.parseInt(decimalStringInput);
                                    decimal.setDecimal(decimalIntInput);
                                    System.out.println("The calculator has been set to "+ decimalIntInput +" decimal places.");
                                    break settingLoop;
                                } catch (NumberFormatException e){
                                    System.out.println("Invalid input");
                                }
                            }
                        } else if (settingInput.equalsIgnoreCase("2")) {
                            degreeRadLoop:
                            while(true){
                                System.out.println("\nDegree/Radian");
                                System.out.println(" - 1 for Degree \n" +
                                        " - 2 for Radian \n");
                                String degRadStringInput = scanner.nextLine();
                                if (degRadStringInput.equalsIgnoreCase("") || degRadStringInput==null){
                                    throw new EmptyInputException();
                                } else if (degRadStringInput.equalsIgnoreCase("1")){
                                    DegreeRadian.setRadian(false);
                                    System.out.println("The calculator has been set to Degree mode");
                                    break settingLoop;
                                } else if (degRadStringInput.equalsIgnoreCase("2")) {
                                    DegreeRadian.setRadian(true);
                                    System.out.println("The calculator has been set to Radian mode");
                                    break settingLoop;
                                } else
                                    System.out.println("Invalid input");
                            }
                        } else {
                            System.out.println("Invalid input");
                        }
                    }
                } else if (menuInput.equalsIgnoreCase("q")) {
                    System.out.println("Thank you for using ETERNITY.");
                    history.clear();
                    System.exit(0);
                } else {
                    System.out.println("Invalid input");
                }
            }
            catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
