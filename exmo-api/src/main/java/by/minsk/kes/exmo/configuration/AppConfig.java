package by.minsk.kes.exmo.configuration;

import by.minsk.configuration.ExmoConfig;
import by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import by.minsk.model.AuthPair;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "by.minsk")
@Import({ExmoConfig.class})
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
