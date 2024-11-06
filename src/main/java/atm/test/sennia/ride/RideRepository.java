package atm.test.sennia.ride;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RideRepository extends JpaRepository<Ride,Integer> {
    public List<Ride> findByRideState(RideState rideState);
    @Query("SELECT r FROM Ride r WHERE r.departurePoint.id=:point1 and r.arrivalPoint.id=:point2 and r.rideState=:state")
    public  List<Ride> findRidesBetweenTwoPoints(@Param("point1") int point1,@Param("point2") int point2,@Param("state")RideState state);

}
