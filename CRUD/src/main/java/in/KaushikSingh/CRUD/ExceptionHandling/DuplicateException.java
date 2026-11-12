package in.KaushikSingh.CRUD.ExceptionHandling;

public class DuplicateException extends RuntimeException{

    public DuplicateException(String message){
        super(message);
    }
}
