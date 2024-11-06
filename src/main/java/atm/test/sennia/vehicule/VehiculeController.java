package atm.test.sennia.vehicule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/vehicules")
public class VehiculeController {
    private VehiculeService vehiculeService;
    @Autowired
    public VehiculeController(VehiculeService vehiculeService){
        this.vehiculeService=vehiculeService;
    }
    @GetMapping
    public ResponseEntity<List<VehiculeDTO>> getAllVehicules(){
        List<VehiculeDTO> vehiculeDTOS=this.vehiculeService.getAllVehicules();
        return ResponseEntity.ok(vehiculeDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<VehiculeDTO> findVehiculeById(@PathVariable String id){
        return ResponseEntity.ok(this.vehiculeService.getVehiculeById(id));
    }
    @GetMapping("/available")
    public ResponseEntity<List<VehiculeDTO>> findAvaliableVehicules(){
        return ResponseEntity.ok(this.vehiculeService.findAvaliableVehicules());
    }
    @GetMapping("/{id}/available")
    public ResponseEntity<Boolean> isVehiculeAvaliable(@PathVariable String id){
        return ResponseEntity.ok(this.vehiculeService.isVehiculeAvaliable(id));
    }
    @PostMapping
    public void addVehicule(@RequestBody VehiculeDTO vehiculeDTO){
        this.vehiculeService.addVehicule(vehiculeDTO);
    }

}
