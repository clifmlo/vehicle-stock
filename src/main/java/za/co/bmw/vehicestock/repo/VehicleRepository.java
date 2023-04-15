package za.co.bmw.vehicestock.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.bmw.vehicestock.entity.Vehicle;

/**
 *
 * @author cliff
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    
}
