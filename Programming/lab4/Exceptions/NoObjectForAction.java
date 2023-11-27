package lab4.Exceptions;

public class NoObjectForAction extends RuntimeException{
    public NoObjectForAction(String message){
        super(message);
    }
}
