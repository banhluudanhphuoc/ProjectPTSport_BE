package id.vn.ptsport.PTSport.Exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class InternalServerErrorException extends RuntimeException {
    private Map<String, String> errors;
    public InternalServerErrorException(String msg)
    {
        super(msg);
    }
    public InternalServerErrorException(Map<String, String> params)
    {
        this.errors = params;
    }

}