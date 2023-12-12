package tr.com.provera.pameraapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {

    private String token;

    private String refreshToken;

    private UserDto user;
}
