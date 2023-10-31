import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean loggedIn = false;
        boolean checkedUsername = false;
        int counter = 3;
        int accountNumberToGenerate = 3;
        String username = null;
        String password;
        int option;
        String accountToSend;

        Scanner scanner = new Scanner(System.in);

        Operations operations = new Operations();

        Account account1 = new Account("Andrei", "Balan", "R001", 1000, "AndreiBalan", "password123!");
        Account account2 = new Account("Mihai", "Balan", "R002", 2000, "MihaiBalan", "password123!");

        Bank bank = Bank.getInstance();

        bank.addAccount(account1);
        bank.addAccount(account2);

        //while loop pentru creare conturi si modificare variabila loggedIn dupa ce s-a logat userul
        //plus inca un while pt transactions

        System.out.println("Hello! How can we help you today?");
        while (!loggedIn) {

            int action1;
            try {
                System.out.println("Log In if you have an account or create a new one:\n1.Log In\n2.Create Account");
                action1 = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception anyOtherInput) {
                System.out.println("Invalid Input! Please enter:  1 to Log In or 2 to Create Account");
                break;
            }
            switch (action1) {
                case 1 -> {
                    while (!checkedUsername) {
                        if (counter == 0) {
                            System.out.println("\nYou have entered the wrong username and password 3 times, please try again later");
                            break;
                        }
                        System.out.println("\nEnter your username: ");
                        username = scanner.nextLine();
                        checkedUsername = operations.checkAccount(username);
                        if (checkedUsername) {
                            do {
                                counter--;
                                System.out.println("\nPlease enter your password: ");
                                password = scanner.nextLine();
                                loggedIn = operations.checkPassword(password);
                                if (loggedIn) {
                                    System.out.println("\nYou have successfully Logged In!");
                                } else {
                                    if (counter == 0) {
                                        break;
                                    } else {
                                        System.out.println("\nIncorrect Password!");
                                        System.out.println("\nInvalid username, please try again. You have " + String.valueOf(counter) + " tries left.");
                                    }
                                }
                            } while (!loggedIn);
                        } else {
                            counter--;
                            System.out.println("\nInvalid username, please try again. You have " + String.valueOf(counter) + " tries left.");
                            if (counter == 0) {
                                break;
                            }
                        }
                    }
                }
                case 2 -> {
                    System.out.println("\nPlease enter your first name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("\nPlease enter your first name: ");
                    String lastName = scanner.nextLine();
                    accountNumberToGenerate++;
                    String accountNumberNew = "R00" + String.valueOf(accountNumberToGenerate);
                    System.out.println("\nPlease enter your username: ");
                    String usernameNew = scanner.nextLine();
                    System.out.println("\nPlease enter your password: ");
                    String passwordNew = scanner.nextLine();
                    System.out.println("\nWould you like to add money to your brand new account: ");
                    double creditNew = scanner.nextDouble();
                    scanner.nextLine();
                    operations.createAccount(firstName, lastName, accountNumberNew, creditNew, usernameNew, passwordNew);
                }
            }
            while(loggedIn){
                Account account = bank.getAccountByUsername(username);
                System.out.println("\nWhat would you like to do?: \n1.Withdraw \n2.Deposit \n3.Transfer \n4.Get a loan\n5.LogOut");
                try{
                    option=scanner.nextInt();
                    scanner.nextLine();
                }catch (InputMismatchException a) {
                    System.out.println("\nInvalid Input!");
                    break;
                }
                switch (option) {
                    case 1 -> {
                        try {
                            System.out.println("\nHow much would you like to withdraw: ");
                            double amount = scanner.nextDouble();
                            scanner.nextLine();
                            operations.withDraw(account, amount);

                        } catch (InsufficientFundsException ime) {
                            System.out.println(ime.getMessage());
                        } catch (InputMismatchException a) {
                            System.out.println("\nInvalid Input!");
                        }
                    }
                    case 2 -> {
                        try {
                            System.out.println("\nHow much would you like to add: ");
                            double amount = scanner.nextDouble();
                            scanner.nextLine();
                            operations.addBalance(account, amount);

                        } catch (InputMismatchException ime) {
                            System.out.println("\nInvalid Input!");
                        }
                    }
                    case 3 -> {
                        try {
                            Account accountReciever;
                            System.out.println("\nTo what account would you like to make the transfer?: ");
                            accountToSend = scanner.nextLine();
                            accountReciever = bank.getAccountByUsername(accountToSend);
                            if (accountReciever != null) {
                                System.out.println("\nHow much money would you like to send?: ");
                                double amount = scanner.nextDouble();
                                scanner.nextLine();
                                operations.transfer(account, accountReciever, amount);
                            } else {
                                System.out.println("\nThere is not such account!");
                            }
                        } catch (InsufficientFundsException e) {
                            System.out.println(e.getMessage());
                        } catch (InputMismatchException a) {
                            System.out.println("\nInvalid Input!");
                        }
                    }
                    case 4 -> {
                        try {
                            operations.getLoan(account);
                        } catch (InputMismatchException a) {
                            System.out.println("\nInvalid Input!");
                        }
                    }
                    case 5->{
                        loggedIn=false;
                    }
                }
                }
                System.out.println("\nWould you like to do anything else?(Yes/No): ");
                String finish=scanner.nextLine();
                    if (finish.equalsIgnoreCase("yes")) {
                        continue;
                    } else if (finish.equalsIgnoreCase("no")) {
                        break;
                    } else {
                        System.out.println("\nInvalid input!");
                        break;
                    }
                }
            }

        }
