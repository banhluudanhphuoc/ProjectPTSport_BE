package id.vn.ptsport.PTSport.DTO.Response;

import id.vn.ptsport.PTSport.Entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInfoResponse {

    private String username;
    private String email;
    private List<Role> roles;
}
