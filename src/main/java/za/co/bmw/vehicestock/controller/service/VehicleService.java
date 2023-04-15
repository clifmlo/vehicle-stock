package za.co.bmw.vehicestock.controller.service;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.bmw.vehicestock.dto.VehicleDto;
import za.co.bmw.vehicestock.entity.Vehicle;
import za.co.bmw.vehicestock.entity.VehicleDetail;
import za.co.bmw.vehicestock.repo.VehicleRepository;

/**
 *
 * @author cliff
 */
@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    public Vehicle addVehicle(final VehicleDto vehicleDto) {  
        Vehicle vehicle = new Vehicle();
        vehicle.setStockNumber(generateStockNumber());
        vehicle.setVehicleDetails(buildVehicleDetails(vehicleDto));
        
        return vehicleRepository.save(vehicle);
    }
    
    public Vehicle getVehicle(final long id) {
        return vehicleRepository.findById(id).get();
    }
    
    public Vehicle updateVehicle(final Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(long id) {
        vehicleRepository.deleteById(id);
    }
    
    private VehicleDetail buildVehicleDetails(final VehicleDto vehicleDto) {
        return VehicleDetail.builder()
                .model(vehicleDto.getModel())
                .modelYear(vehicleDto.getModelYear())
                .vinNumber(vehicleDto.getVinNumber())
                .engineNumber(vehicleDto.getEngineNumber())
                .engineCapacity(vehicleDto.getEngineCapacity())
                .fuelType(vehicleDto.getFuelType())
                .build();
    }

    private String generateStockNumber() {
        Random rand = new Random();       
        return String.valueOf(1000000000 + (int) (rand.nextDouble() * 999999999)); 
    }
        
}
