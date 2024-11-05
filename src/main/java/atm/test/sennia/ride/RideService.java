package atm.test.sennia.ride;

import atm.test.sennia.Mapper;
import atm.test.sennia.driver.DriverService;
import atm.test.sennia.exception.ResourceNotFoundException;
import atm.test.sennia.location.LocationService;
import atm.test.sennia.user.User;
import atm.test.sennia.user.UserDTO;
import atm.test.sennia.user.UserService;
import atm.test.sennia.vehicule.Vehicule;
import atm.test.sennia.vehicule.VehiculeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@NoArgsConstructor
public class RideService {
    private RideRepository rideRepository;
    private DriverService driverService;
    private VehiculeService vehiculeService;
    private LocationService locationService;
    private UserService userService;
    @Autowired
    public RideService(RideRepository rideRepository,DriverService driverService,VehiculeService vehiculeService,LocationService locationService){
        this.rideRepository=rideRepository;
        this.driverService=driverService;
        this.vehiculeService=vehiculeService;
        this.locationService=locationService;
    }
    public List<RideDTO> getAllRides(){
        return this.rideRepository.findAll()
                .stream().map(Mapper::toRideDTO)
                .collect(Collectors.toList());
    }
    public Ride findRideById(int id){
        Optional<Ride> ride=this.rideRepository.findById(id);
        if (ride.isEmpty()){
            throw new ResourceNotFoundException("Ride not found");
        }
        return  ride.get();
    }
    public RideDTO getRideById(int id){
        return Mapper.toRideDTO(this.findRideById(id));
    }
    public List<RideDTO> findActiveRides(){
        return this.rideRepository.findByRideState(RideState.OnGoing)
                .stream().map(Mapper::toRideDTO)
                .collect(Collectors.toList());
    }
    public List<RideDTO> findFinishedRides(){
        return this.rideRepository.findByRideState(RideState.Done)
                .stream().map(Mapper::toRideDTO)
                .collect(Collectors.toList());
    }
    public List<RideDTO> findCancelledRides(){
        return this.rideRepository.findByRideState(RideState.Cancelled)
                .stream().map(Mapper::toRideDTO)
                .collect(Collectors.toList());
    }
    public List<RideDTO> findScheduledRides(){
        return this.rideRepository.findByRideState(RideState.scheduled)
                .stream().map(Mapper::toRideDTO)
                .collect(Collectors.toList());
    }
    public List<RideDTO> findScheduledRidesBetweenTwoPoints(int point1,int point2){
    return this.rideRepository.findRidesBetweenTwoPoints(point1,point2,RideState.scheduled)
            .stream().map(Mapper::toRideDTO)
            .collect(Collectors.toList());
    }

    public void addPersonToRide(int rideId, User user){
        Optional<Ride> OptionalRide= this.rideRepository.findById(rideId);
        if(OptionalRide.isEmpty()){
            throw new ResourceNotFoundException("Ride not found");
        }
        Ride ride  =OptionalRide.get();
        Vehicule vehicule=ride.getVehicule();
        if(vehicule.getMaxSeats()>ride.getUsers().size()){
            ride.addUser(user);
            this.rideRepository.save(ride);
        }

    }
    public void scheduleARide(RideDTO rideDTO){
        Ride ride =Mapper.toRide(rideDTO);
        ride.setDriver(this.driverService.findDriverById(rideDTO.getDriver().getId()));
        ride.setVehicule(this.vehiculeService.findVehiculeById(rideDTO.getVehicule().getId()));
        ride.setDeparturePoint(this.locationService.findLocationById(rideDTO.getDeparturePoint().getId()));
        ride.setArrivalPoint(this.locationService.findLocationById(rideDTO.getArrivalPoint().getId()));
        List< User> users=new ArrayList<>();
        for (UserDTO userDTO: rideDTO.getUsers()){
            users.add(userService.findByEmail(userDTO.getEmail()));
        }
        ride.setUsers(users);
        ride.setRideState(RideState.scheduled);
    }
    public void StartRide(int id){
        Ride ride=this.findRideById(id);
        ride.setRideState(RideState.OnGoing);
        ride.setDepartureTime(new Date());

    }
    public void EndRide(int id){
        Ride ride=this.findRideById(id);
        ride.setRideState(RideState.Done);
        ride.setArivalTime(new Date());
    }

}
