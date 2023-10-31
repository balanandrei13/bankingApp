public class InsufficientFundsException extends Exception{
    public String getMessage(){
        return "\nInsufficient funds.\nYour balance doesn't match your lifestyle...\n";
    }
}
