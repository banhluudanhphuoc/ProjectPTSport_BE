package id.vn.ptsport.PTSport.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordDTO implements Serializable {
    @NotNull
    private String newPassword;
    @NotNull
    private String oldPassword;
}
