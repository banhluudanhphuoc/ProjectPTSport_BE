//package id.vn.ptsport.PTSport.DTO.Response;
//
//
//public class LoginResponse {
//    private String token;
//
//    public LoginResponse(String token) {
//        this.token = token;
//    }
//
//    public String getToken() {
//        return token;
//    }
//}
//

//package id.vn.ptsport.PTSport.DTO.Response;
//
//
//public class LoginResponse {
//    private String token;
//
//    public LoginResponse(String token) {
//        this.token = token;
//    }
//
//    public String getToken() {
//        return token;
//    }
//}
//

package id.vn.ptsport.PTSport.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;

    private String message;
    private int status;
}