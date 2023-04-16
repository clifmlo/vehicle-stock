package za.co.bmw.vehicestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class VehiceStockApplication {

    public static void main(String[] args) {
            SpringApplication.run(VehiceStockApplication.class, args);
    }

}
