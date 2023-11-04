import java.util.LinkedList;
import java.util.List;

public class Bank {
    private static Bank instance=null;
    private List<Account> accounts = new LinkedList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account a) {
        this.accounts.add(a);
    }

    public void deleteAccounts(Account a) {
        this.accounts.remove(a);
    }

    public Account getAccountByUsername(String username){
        for(Account account:this.accounts){
            if (account.getUsername().equals(username)){
                return account;
            }

        }
        return null;
    }
    public Account getAccountByAccountNumber(String accountNumber){
        for(Account account:this.accounts){
            if (account.getAccountNumber().equals(accountNumber)){
                return account;
            }

        }
        return null;
    }



    public void accountToString() {
        for (Account account : accounts) {
            System.out.println(account + " ");
        }
    }
    public static Bank getInstance(){
        if(instance==null){
            instance=new Bank();
        }
        return instance;
    }
}
