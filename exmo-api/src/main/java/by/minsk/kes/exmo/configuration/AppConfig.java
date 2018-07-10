package by.minsk.kes.exmo.configuration;

import by.minsk.kes.exmo.env.configuration.ExmoConfig;
import by.minsk.kes.coinmarket.configuration.CoinMarketConfig;
import by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "by.minsk.kes.exmo")
@Import({ExmoConfig.class, CoinMarketConfig.class})
@EnableMBeanExport
public class AppConfig {

    @Value("${exmo.key:key}")
    private String key;

    @Value("${exmo.secret:secret}")
    private String secret;

    @Bean
    ExmoRestAdapter exmoRestAdapter() {
        return new ExmoRestAdapter(key, secret);
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
