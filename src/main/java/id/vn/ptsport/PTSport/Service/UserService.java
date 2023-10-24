package id.vn.ptsport.PTSport.Service;

import id.vn.ptsport.PTSport.DTO.Request.UserRegistrationRequest;
import id.vn.ptsport.PTSport.DTO.Response.UserRegistrationResponse;


public interface UserService {
        UserRegistrationResponse registerUser(UserRegistrationRequest registrationRequest);

    }



