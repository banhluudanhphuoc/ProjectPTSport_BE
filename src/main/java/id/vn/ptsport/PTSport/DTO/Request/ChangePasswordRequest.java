package id.vn.ptsport.PTSport.DTO.Request;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
    // Getter và setter
}

