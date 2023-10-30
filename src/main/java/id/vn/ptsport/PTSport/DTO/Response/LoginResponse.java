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


public class LoginResponse {
    private String token;
    private int status;
    private String message;
    public LoginResponse(String token, String s, int i) {
        this.token = token;
        this.message = message;
        this.status = status;
    }

    public String getToken() {
        return token;
    }
    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
}


