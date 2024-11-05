package atm.test.sennia.vehicule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculeDTO {
   private String id;
   private String brand;
   private String model;
   private int maxSeats;
   private boolean isOccupied;
}
