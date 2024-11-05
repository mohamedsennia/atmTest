package atm.test.sennia.vehicule;

import atm.test.sennia.Mapper;
import atm.test.sennia.exception.ResourceNotFoundException;
import atm.test.sennia.ride.RideState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class VehiculeService {
    private VehiculeRpository vehiculeRpository;
    @Autowired
    public VehiculeService(VehiculeRpository vehiculeRpository){
        this.vehiculeRpository=vehiculeRpository;
    }
    public List<VehiculeDTO> getAllVehicules(){
        return this.vehiculeRpository.findAll()
                .stream().map(vehicule -> {
                    VehiculeDTO vehiculeDTO= Mapper.toVehiculeDto(vehicule);
                    vehiculeDTO.setOccupied(this.isVehiculeAvaliable(vehiculeDTO.getId()));
                    return  vehiculeDTO;
                })
                .collect(Collectors.toList());
    }
    public Vehicule findVehiculeById(String id){
        Optional<Vehicule> vehicule=this.vehiculeRpository.findById(id);
        if(!vehicule.isPresent()){
            throw new ResourceNotFoundException("Vehicule not found");
        }
        return vehicule.get();
    }
    public VehiculeDTO getVehiculeById(String id){
        VehiculeDTO vehiculeDTO= Mapper.toVehiculeDto(this.findVehiculeById(id));
        vehiculeDTO.setOccupied(this.isVehiculeAvaliable(vehiculeDTO.getId()));
        return  vehiculeDTO;
    }
    public boolean isVehiculeAvaliable(String id){
        return this.vehiculeRpository.isVehiculeAvaliable(id,RideState.OnGoing,RideState.scheduled);
    }
    public List<VehiculeDTO> findAvaliableVehicules(){
        return this.vehiculeRpository.findAvaliableVehicules(RideState.OnGoing,RideState.scheduled)
                .stream().map(vehicule -> {
                    VehiculeDTO vehiculeDTO= Mapper.toVehiculeDto(vehicule);
                    vehiculeDTO.setOccupied(this.isVehiculeAvaliable(vehiculeDTO.getId()));
                    return  vehiculeDTO;
                })
                .collect(Collectors.toList());
    }
    public void addVehicule(VehiculeDTO vehiculeDTO){
                this.vehiculeRpository.save(Mapper.toVehicule(vehiculeDTO));
    }
}
