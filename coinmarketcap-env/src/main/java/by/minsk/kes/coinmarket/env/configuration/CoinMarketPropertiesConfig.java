package by.minsk.kes.coinmarket.env.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySources({
        @PropertySource("classpath:modules/coinmarket.properties"),
        @PropertySource("classpath:modules/coinmarket-auth.properties")
})
public class CoinMarketPropertiesConfig {

    @Bean
    static PropertySourcesPlaceholderConfigurer coinMarketPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
