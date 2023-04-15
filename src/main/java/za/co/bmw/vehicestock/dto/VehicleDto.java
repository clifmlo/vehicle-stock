package za.co.bmw.vehicestock.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author cliff
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    
    private long id;
    
    @NotNull
    private String model;
    
    @NotNull
    private int modelYear;
    
    @NotNull
    private String vinNumber;
    
    @NotNull
    private String engineNumber;
    
    @NotNull
    private double engineCapacity;
    
    @NotNull
    private String fuelType;
}
