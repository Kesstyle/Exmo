package by.minsk.kes.coinmarket.env.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:modules/coinmarket.properties")
public class CoinMarketPropertiesConfig {

    @Bean
    PropertySourcesPlaceholderConfigurer coinMarketPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
