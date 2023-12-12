package tr.com.provera.pameraapi.util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTAuthority {

    private String role;
    private List<String> privileges;
}
