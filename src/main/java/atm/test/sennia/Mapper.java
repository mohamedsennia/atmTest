package atm.test.sennia;

import atm.test.sennia.driver.Driver;
import atm.test.sennia.driver.DriverDTO;
import atm.test.sennia.location.Location;
import atm.test.sennia.location.LocationDTO;
import atm.test.sennia.ride.Ride;
import atm.test.sennia.ride.RideDTO;
import atm.test.sennia.user.User;
import atm.test.sennia.user.UserDTO;
import atm.test.sennia.vehicule.Vehicule;
import atm.test.sennia.vehicule.VehiculeDTO;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static VehiculeDTO toVehiculeDto(Vehicule vehicule){

        return  new VehiculeDTO(vehicule.getId(), vehicule.getBrand(), vehicule.getModel(), vehicule.getMaxSeats(),false);
    }
    public static Vehicule toVehicule(VehiculeDTO vehiculeDTO){

        return new Vehicule(vehiculeDTO.getId(), vehiculeDTO.getBrand(), vehiculeDTO.getModel(), vehiculeDTO.getMaxSeats());
    }
    public static LocationDTO toLocationDTO(Location location){
        return  new LocationDTO(location.getId(),location.getName());
    }
    public static Location toLocation(LocationDTO locationDTO){
        return new Location(locationDTO.getId(), locationDTO.getName());
    }
    public static DriverDTO toDriverDTO(Driver driver){
        return new DriverDTO(driver.getId(), driver.getFirstName(), driver.getLastName(),false);
    }
    public static Driver toDriver(DriverDTO driverDTO){
        return new Driver(driverDTO.getId(), driverDTO.getFirstName(), driverDTO.getLastName());
    }
    public static UserDTO toUserDTO(User user){
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
    }
    public static RideDTO toRideDTO(Ride ride){
        List<UserDTO> users=new ArrayList<>();
        for(User user:ride.getUsers()){
            users.add(Mapper.toUserDTO(user));
        }
        return new RideDTO(ride.getId(), ride.getRideState(),ride.getDepartureTime(), ride.getArivalTime(),Mapper.toDriverDTO(ride.getDriver()),Mapper.toVehiculeDto(ride.getVehicule()),Mapper.toLocationDTO(ride.getDeparturePoint()),Mapper.toLocationDTO(ride.getArrivalPoint()),users);
    }
    public static Ride toRide(RideDTO rideDTO){
        return new Ride(rideDTO.getId(), rideDTO.getRideState(),rideDTO.getDepartureTime(), rideDTO.getArivalTime());
    }
}
