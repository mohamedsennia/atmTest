package atm.test.sennia.ride;

import atm.test.sennia.driver.Driver;
import atm.test.sennia.location.Location;
import atm.test.sennia.user.User;
import atm.test.sennia.vehicule.Vehicule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ride {
    @Id
    @SequenceGenerator(name = "ride_sequence",sequenceName = "ride_sequence",allocationSize = 1)
    @GeneratedValue(generator = "ride_sequence",strategy = GenerationType.SEQUENCE)
    private int id;
    @Enumerated(EnumType.STRING)
    private RideState rideState;
    private Date departureTime;


    private Date arivalTime;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @ManyToOne
    @JoinColumn(name="vehicule_id")
    private Vehicule vehicule;
    @ManyToOne
    @JoinColumn(name = "departurePoint")
    private Location departurePoint;
    @ManyToOne
    @JoinColumn(name = "arrivalPoint")
    private Location arrivalPoint;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "took_a_ride",
            joinColumns =@JoinColumn(name = "id_ride"),
            inverseJoinColumns = @JoinColumn(name = "id_user")

    )
    private List<User> users;

    public Ride(int id, RideState rideState, Date departureTime,Date arivalTime) {
        this.id = id;
        this.rideState = rideState;
        this.departureTime = departureTime;
        this.arivalTime=arivalTime;
    }

    public void addUser(User user){
        if(vehicule.getMaxSeats()>users.size()){
            users.add(user);
        }
    }



}
