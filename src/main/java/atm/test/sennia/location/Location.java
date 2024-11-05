package atm.test.sennia.location;

import atm.test.sennia.ride.Ride;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    @SequenceGenerator(name = "location_sequence",sequenceName = "location_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "location_sequence")
     private int id;
    private String name; // this is simplified, we could have the location geo points so we could do more calculations
    @OneToMany(mappedBy = "departurePoint")
    private List<Ride> ridesFrom;
    @OneToMany(mappedBy = "arrivalPoint")
    private List<Ride> ridesTo;
    public  Location(int id,String name){
        this.id=id;
        this.name=name;
        this.ridesFrom=new ArrayList<>();
        this.ridesTo=new ArrayList<>();
    }

}
