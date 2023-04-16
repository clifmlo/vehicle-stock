package za.co.bmw.vehicestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author cliff
 */
@Data
@AllArgsConstructor
public class ApiError {
    private String errorCode;
    private String errorMessage;   
}
