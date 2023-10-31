import java.util.List;
import java.util.Scanner;

public class Operations {
    Bank bank= Bank.getInstance();
    public void transfer(Account a, Account b, double amount) throws InsufficientFundsException {
        if(a.getCredit()<amount){
            throw new InsufficientFundsException();
        }
        a.setCredit(a.getCredit() - amount);
        b.setCredit(b.getCredit() + amount);
        System.out.println("You have transfered " + b.getAccountNumber() + ": " + amount + " RON");
        System.out.println("Ammount left: " + a.getCredit() + " RON");
    }

    public void withDraw(Account a, double amount) throws InsufficientFundsException {
        if(a.getCredit()<amount){
            throw new InsufficientFundsException();
        }
        a.setCredit(a.getCredit() - amount);
        System.out.println("You have withdrawn " + amount + " RON " + "from your account: " + a.getAccountNumber());
        System.out.println("Ammount left: " + a.getCredit() + " RON");
    }


    public void addBalance(Account a, double amount) {
        a.setCredit(a.getCredit() + amount);
        System.out.println("Your top-up for the amount of: " + amount + " RON was successful and your credit is now " + a.getCredit() + " RON");
    }
    public void getLoan(Account a) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What amount would interest you?");
        double c=scanner.nextDouble();
        scanner.nextLine();

        System.out.println("For how many months?\n6 months\n12 months\n24 months");
        double time = scanner.nextDouble();
        scanner.nextLine();
        double interestTotal = c * 13.5 / 100;
        double interestPerMonth = 0;
        double monthlyPayments = 0;
        double totalPayment = 0;
        if (time == 6 || time == 12 || time == 24) {
            interestPerMonth = (c * 13.5 / 100) / time;
            monthlyPayments = (c + (c * 13.5 / 100)) / time;
            totalPayment = (c + (c * 13.5 / 100));

        } else {
            System.out.println("Invalid Input");
        }
        System.out.printf("Your loan has been approved and " + c + "RON have been added to your account.\n" +
                "Your total interest is " + "%.2f" + "RON.\n" +
                "Your monthly interest is " + "%.2f" + "RON.\n" +
                "Your monthlyPayment is " + "%.2f" + "RON\n" +
                "Your total payment is  " + "%.2f" + "RON",interestTotal,interestPerMonth,monthlyPayments,totalPayment);
    }
    public boolean checkAccount(String username){
        List<Account> listAccounts=bank.getAccounts();
            for(Account account:listAccounts){
                if(account.getUsername().equals(username)){
                    return true;
                }
             }
        return false;
    }

    public boolean checkPassword(String password){
        List<Account> listAccounts=bank.getAccounts();
        for(Account account:listAccounts){
            if(account.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public void createAccount(String firstName, String lastName,String account,
                                 double credit,String username, String password){
        Account newAccount=new Account(firstName,lastName,account,credit,username,password);
        bank.addAccount(newAccount);
    }
}
