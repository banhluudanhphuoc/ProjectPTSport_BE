package id.vn.ptsport.PTSport.DTO.Request;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String username;
    private String password;
    private String email;
    private String name;
    private String phoneNumber;
    private String dateOfBirth;
}
