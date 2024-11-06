package atm.test.sennia.vehicule;

import atm.test.sennia.ride.RideState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehiculeRpository extends JpaRepository<Vehicule,String> {
    @Query("SELECT v FROM Vehicule v WHERE v.id NOT IN (SELECT r.vehicule.id FROM Ride r WHERE r.rideState IN (:onGoing, :scheduled))")
    public List<Vehicule> findAvaliableVehicules(@Param("onGoing") RideState onGoing, @Param("scheduled") RideState scheduled);

    @Query("SELECT CASE WHEN COUNT(r)>0 THEN false ELSE true END FROM Ride r WHERE r.vehicule.id=:vehiculeId and (r.rideState=:onGoing or r.rideState=:scheduled) ")
    public boolean isVehiculeAvaliable(@Param("vehiculeId") String vehiculeId,@Param("onGoing")RideState onGoing,@Param("scheduled") RideState scheduled );
}