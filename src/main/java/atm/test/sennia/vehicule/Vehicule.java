package atm.test.sennia.vehicule;

import atm.test.sennia.ride.Ride;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicule {
    @Id
    String id;
    String brand;
    String model;
    int maxSeats;
    @OneToMany(mappedBy = "vehicule")
    private List<Ride> rides;
    public Vehicule(String id,String brand,String model,int maxSeats){
        this.id=id;
        this.brand=brand;
        this.model=model;
        this.maxSeats=maxSeats;
        this.rides=new ArrayList<Ride>();
    }
    public Vehicule(String brand,String model,int maxSeats){

        this.brand=brand;
        this.model=model;
        this.maxSeats=maxSeats;
        this.rides=new ArrayList<Ride>();
    }

}
