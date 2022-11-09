package tr.fibabanka.components;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tr.fibabanka.config.Configurations;
import tr.fibabanka.dtos.ProductDto;

import java.util.List;

@Component
public class MyRestTemplate {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Configurations configurations;

    public MyRestTemplate(Configurations configurations) {
        this.configurations = configurations;
    }

    public List<ProductDto> findProductsByCategoryId(long categoryId) {
        String productAppBaseUrl = configurations.getInventoryAppBaseUrl();
        return restTemplate.getForEntity(productAppBaseUrl + "/products/" + categoryId, List.class)
                .getBody();
    }

}
