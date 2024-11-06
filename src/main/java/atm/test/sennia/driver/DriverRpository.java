package atm.test.sennia.driver;

import atm.test.sennia.ride.RideState;
import atm.test.sennia.vehicule.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRpository extends JpaRepository<Driver,Integer> {
    @Query("SELECT d FROM Driver d WHERE d.id NOT IN (SELECT r.driver.id FROM Ride r WHERE r.rideState IN (:onGoing, :scheduled))")
    public List<Driver> findAvaliableDrivers(@Param("onGoing") RideState onGoing, @Param("scheduled") RideState scheduled);
    @Query("SELECT CASE WHEN COUNT(r)>0 THEN false ELSE true END FROM Ride r WHERE r.driver.id=:driverId and (r.rideState=:onGoing or r.rideState=:scheduled) ")
    public boolean isDriverAvaliable(@Param("driverId") int driverId,@Param("onGoing")RideState onGoing,@Param("scheduled") RideState scheduled );
}
