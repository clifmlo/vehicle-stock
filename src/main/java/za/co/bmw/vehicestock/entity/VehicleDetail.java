package za.co.bmw.vehicestock.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



/**
 *
 * @author cliff
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Transient
    private final String make = "BMW";
    
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
    
    @CreationTimestamp
    private LocalDateTime date_created;
    
    @UpdateTimestamp
    private LocalDateTime date_updated;
    
}
