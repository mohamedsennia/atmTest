package atm.test.sennia.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(("/api/v1/drivers"))
public class DriverController {
    private DriverService driverService;
    @Autowired
    public DriverController(DriverService driverService){
        this.driverService=driverService;
    }
    @GetMapping
    public ResponseEntity<List<DriverDTO>> findAllDrivers(){
        return ResponseEntity.ok(this.driverService.findAllDrivers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> getDriver(@PathVariable int id){
        return ResponseEntity.ok(this.driverService.getDriver(id));
    }
    @GetMapping("/available")
    public ResponseEntity<List<DriverDTO>>  findAvaliableDrivers(){
        return ResponseEntity.ok(this.driverService.findAvaliableDrivers());
    }
    @GetMapping("/{id}/available")
    public ResponseEntity<Boolean> isDriverAvaliable(int id){
        return ResponseEntity.ok(this.driverService.isDriverAvaliable(id));
    }
    @PostMapping
    public void addDriver(@RequestBody DriverDTO driverDTO){
        this.driverService.addDriver(driverDTO);
    }
}
