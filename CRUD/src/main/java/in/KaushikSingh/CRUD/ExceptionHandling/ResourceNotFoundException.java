package in.KaushikSingh.CRUD.ExceptionHandling;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }
}
