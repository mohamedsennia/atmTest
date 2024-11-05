package atm.test.sennia.location;

import atm.test.sennia.Mapper;
import atm.test.sennia.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private LocationRepository locationRepository;
    @Autowired
    public LocationService(LocationRepository locationRepository){
        this.locationRepository=locationRepository;
    }
    public Location findLocationById(int id){
        Optional<Location> location=this.locationRepository.findById(id);
        if(location.isEmpty()){
            throw  new ResourceNotFoundException("Location Not Found");
        }
        return  location.get();
    }
    public List<LocationDTO> getAllLocations(){
        return this.locationRepository.findAll()
                .stream().map(Mapper::toLocationDTO)
                .collect(Collectors.toList());
    }
    public LocationDTO getLocationById(int id){
     return    Mapper.toLocationDTO(this.findLocationById(id));
    }
    public void addLocation(LocationDTO locationDTO){
        this.locationRepository.save(Mapper.toLocation(locationDTO));
    }
}
