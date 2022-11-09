package tr.fibabanka.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class Configurations {
    @Value("${product-app.base-url}")
    private String inventoryAppBaseUrl;

    @Value("${shopping-app.base-url}")
    private String shoppingAppBaseUrl;
}
