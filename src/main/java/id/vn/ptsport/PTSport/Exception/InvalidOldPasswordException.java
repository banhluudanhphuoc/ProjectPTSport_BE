package id.vn.ptsport.PTSport.Exception;

public class InvalidOldPasswordException extends RuntimeException{
    public InvalidOldPasswordException(String msg)
    {
        super(msg);
    }
}
