package atm.test.sennia.driver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {
    private int id;
    private String firstName;
    private String lastName;
    boolean isAvaliable;

}
