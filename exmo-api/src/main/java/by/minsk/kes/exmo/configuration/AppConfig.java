package by.minsk.kes.exmo.configuration;

import by.minsk.configuration.ExmoConfig;
import by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import by.minsk.model.AuthPair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "by.minsk")
@Import(ExmoConfig.class)
@EnableMBeanExport
public class AppConfig {

    @Value("${exmo.key}")
    private String key;

    @Value("${exmo.secret}")
    private String secret;

    @Bean
    AuthPair authDataSource() {
        final AuthPair authPair = new AuthPair();
        authPair.setKey(key);
        authPair.setSecret(secret);
        return authPair;
    }

    @Bean
    public ExmoRestAdapter exmoRestAdapter() {
        final ExmoRestAdapter e = new ExmoRestAdapter("key", "secret");
        return e;
    }
}
