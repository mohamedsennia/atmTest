package atm.test.sennia.ride;

import atm.test.sennia.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(("/api/v1/rides"))

public class RideController {
    private RideService rideService;
     @Autowired
    public RideController(RideService rideService){
         this.rideService=rideService;
     }
     @GetMapping
    public ResponseEntity<List<RideDTO>> getAllRides(){
         return ResponseEntity.ok(this.rideService.getAllRides());
     }
     @GetMapping("/{id}")
    public ResponseEntity<RideDTO> getRideById(@PathVariable int id){
         return ResponseEntity.ok(this.rideService.getRideById(id));
     }
     @GetMapping("/finished")
    public ResponseEntity<List<RideDTO>> findFinishedRides(){
         return ResponseEntity.ok(this.rideService.findFinishedRides());
     }
     @GetMapping("/cancelled")
     public ResponseEntity<List<RideDTO>> findCancelledRides(){
         return ResponseEntity.ok(this.rideService.findCancelledRides());
     }
     @GetMapping("/Scheduled")
     public ResponseEntity<List<RideDTO>> findScheduledRides(){
         return ResponseEntity.ok(this.rideService.findScheduledRides());
     }
     @GetMapping("/RideBetween/{p1}/{p2}")
    public ResponseEntity<List<RideDTO>> findScheduledRidesBetweenTwoPoints(@PathVariable(name = "p1") int point1,@PathVariable(name = "p2") int point2) {
        return ResponseEntity.ok(this.rideService.findScheduledRidesBetweenTwoPoints(point1, point2));
    }
    @PostMapping
     public void scheduleARide(@RequestBody RideDTO rideDTO){
         this.rideService.scheduleARide(rideDTO);
     }
     @PutMapping("/{id}/addPerson")
    public void addPersonToRide(@PathVariable int id, @RequestBody UserDTO userDTO){
         this.rideService.addPersonToRide(id,userDTO);
     }
     @PutMapping("/{id}/start")
    public void StartRide(@PathVariable int id){
         this.rideService.StartRide(id);
     }
     @PutMapping("/{id}/end")
    public void EndRide(@PathVariable int id){
         this.rideService.EndRide(id);
     }
}
