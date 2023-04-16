package za.co.bmw.vehicestock.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.bmw.vehicestock.service.VehicleService;
import za.co.bmw.vehicestock.dto.VehicleDto;
import za.co.bmw.vehicestock.entity.Vehicle;
import za.co.bmw.vehicestock.model.ApiError;

/**
 *
 * @author cliff
 */
@RestController
@RequestMapping("/api/v1/vehicle")
public class VehiclesController {
    @Autowired
    VehicleService vehicleService;
    
    @PostMapping("/add")
    public ResponseEntity addVehicle(@Valid @RequestBody final VehicleDto vehicle){
        return new ResponseEntity(vehicleService.addVehicle(vehicle), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getVehicle(@PathVariable("id") final long id){
        
        Vehicle vehicle = vehicleService.getVehicle(id);
        
        if (vehicle == null) {
            return new ResponseEntity(new ApiError("404", "Record not found"), HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity(vehicle, HttpStatus.OK);
    }
    
    @PutMapping("/update")
    public ResponseEntity updateVehicle(@Valid @RequestBody final Vehicle vehicle){
        return new ResponseEntity(vehicleService.updateVehicle(vehicle), HttpStatus.OK);
    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity updateVehicle(@PathVariable("id") final long id){
        vehicleService.deleteVehicle(id);
        return new ResponseEntity("Record deleted", HttpStatus.OK);
    } 
}
