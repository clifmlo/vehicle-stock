package za.co.bmw.vehicestock.dto;

import javax.validation.constraints.NotEmpty;
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
    
    @NotEmpty(message = "Model is required")
    private String model;
    
    @NotNull(message = "Model year is required")
    private Integer modelYear;
    
    @NotEmpty(message = "Vin number is required")
    private String vinNumber;
    
    @NotEmpty(message = "Engine number is required")
    private String engineNumber;
    
    @NotNull(message = "Engine capacity is required")
    private Double engineCapacity;
    
    @NotEmpty(message = "Fuel type is required")
    private String fuelType;
}
