package atm.test.sennia.driver;

import atm.test.sennia.Mapper;
import atm.test.sennia.exception.ResourceNotFoundException;
import atm.test.sennia.ride.RideState;
import atm.test.sennia.vehicule.Vehicule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverService {
    private DriverRpository driverRpository;
    public DriverService(DriverRpository driverRpository){
        this.driverRpository=driverRpository;
    }
    public Driver findDriverById(int id){
        Optional<Driver> driver=this.driverRpository.findById(id);
        if(driver.isEmpty()){
            throw new ResourceNotFoundException("driver not found");
        }
       return driver.get();
    }
    public DriverDTO getDriver(int id){
        return Mapper.toDriverDTO(this.findDriverById(id));
    }
    public boolean isDriverAvaliable(int id){
        return this.driverRpository.isDriverAvaliable(id, RideState.OnGoing,RideState.scheduled);
    }
    public List<DriverDTO> findAvaliableDrivers(){
        return this.driverRpository.findAvaliableDrivers(RideState.OnGoing,RideState.scheduled)
                .stream().map(driver -> {
                    DriverDTO driverDTO=Mapper.toDriverDTO(driver);
                    driverDTO.setAvaliable(isDriverAvaliable(driver.getId()));
                    return driverDTO;
                })
                .collect(Collectors.toList());
    }
    public List<DriverDTO> findAllDrivers(){
        return  this.driverRpository.findAll()
                .stream().map(driver -> {
                    DriverDTO driverDTO=Mapper.toDriverDTO(driver);
                    driverDTO.setAvaliable(isDriverAvaliable(driver.getId()));
                    return driverDTO;
                })
                .collect(Collectors.toList());
    }
    public void addDriver(DriverDTO driverDTO){
        this.driverRpository.save(Mapper.toDriver(driverDTO));
    }
}
