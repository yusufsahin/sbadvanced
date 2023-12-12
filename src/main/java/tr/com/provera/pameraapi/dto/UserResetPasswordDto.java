package tr.com.provera.pameraapi.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@ApiModel(value = "UserForgotPassword Data Transfer Object")
public class UserResetPasswordDto {
    //@ApiModelProperty(value = "Username Or Email of User")
    private UUID forgotPasswordGuid;

    //@ApiModelProperty(value = "Username Or Email of User")
    private String newPassword;

    //@ApiModelProperty(value = "Username Or Email of User")
    private String confirmPassword;
}
