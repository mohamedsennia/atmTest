package atm.test.sennia.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController("/api/v1/locations")
public class LocationController {
    private LocationService locationService;
    @Autowired
    public LocationController(LocationService locationService){
        this.locationService=locationService;
    }
    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations(){
        return ResponseEntity.ok(this.locationService.getAllLocations());
    }
    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable int id){
        return ResponseEntity.ok(this.locationService.getLocationById(id));
    }
    @PostMapping
    public void addLocation(@RequestBody LocationDTO locationDTO){
        this.locationService.addLocation(locationDTO);
    }
}
