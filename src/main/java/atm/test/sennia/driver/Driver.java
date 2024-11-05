package atm.test.sennia.driver;

import atm.test.sennia.ride.Ride;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver {
@Id
    @SequenceGenerator(name = "driver_sequence",sequenceName = "driver_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "driver_sequence")
    private int id;
    private String firstName;
    private String lastName;
// not doing the details just to gain time ( DOB....)

    @OneToMany(mappedBy = "driver")
    private List<Ride> rides;
    public Driver(int id,String firstName,String lastName){
        this.firstName=firstName;
        this.id=id;

        this.lastName=lastName;
        this.rides=new ArrayList<>();
    }

}
