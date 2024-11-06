package atm.test.sennia.auth;

import atm.test.sennia.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CostumeResponse {
    private String token;
    private Role role;
}
