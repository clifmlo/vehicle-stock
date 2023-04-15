package za.co.bmw.vehicestock.service;

import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.springframework.beans.factory.annotation.Autowired;
import za.co.bmw.vehicestock.dto.VehicleDto;
import za.co.bmw.vehicestock.entity.Vehicle;

/**
 *
 * @author cliff
 */
@SpringBootTest  
public class VehicleServiceTest {
    @Autowired
    VehicleService vehicleService;
    
    @Test
    void shouldAddReadUpdateDeleteVehicle(){
        
        //Create
        vehicleService.addVehicle(buildVehicleDetails());
        
        //Read
        Vehicle bmw_X6 = vehicleService.getVehicle(1l);
        
        //Assert
        assertNotNull(bmw_X6.getId());
        Assert.assertEquals("X6", bmw_X6.getVehicleDetails().getModel());
        
         //Update
        bmw_X6.getVehicleDetails().setModel("M5");
        vehicleService.updateVehicle(bmw_X6);
       
        
        //Assert
        Vehicle bmw_M5 = vehicleService.getVehicle(1l);
        Assert.assertEquals("M5", bmw_M5.getVehicleDetails().getModel());
        
        
        //Delete
         vehicleService.deleteVehicle(1l);    
        
        //Assert
        Vehicle vehicle = vehicleService.getVehicle(1l);
        assertNull(vehicle);
    } 
    
    private VehicleDto buildVehicleDetails() {
        return VehicleDto.builder()
                .model("X6")
                .modelYear(2023)
                .vinNumber("BD900000TY771")
                .engineNumber("LT765400")
                .engineCapacity(4.4)
                .fuelType("Diesel")
                .build();
    }
    
}
    

