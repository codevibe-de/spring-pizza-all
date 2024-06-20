package c60_config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("app.order")
public record OrderProperties(
        Integer deliveryTimeInMinutes,
        List<String> discountDays,
        Double discountRate
) {
}
