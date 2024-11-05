package atm.test.sennia.ride;

import atm.test.sennia.driver.DriverDTO;
import atm.test.sennia.location.LocationDTO;
import atm.test.sennia.user.UserDTO;
import atm.test.sennia.vehicule.VehiculeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDTO {
    private int id;
    private RideState rideState;
    private Date departureTime;
    private Date arivalTime;
    private DriverDTO driver;
    private VehiculeDTO vehicule;
    private LocationDTO departurePoint;
    private LocationDTO arrivalPoint;
    private List<UserDTO> users;

}
