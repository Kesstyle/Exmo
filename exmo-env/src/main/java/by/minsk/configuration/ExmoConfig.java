package by.minsk.configuration;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySources({
        @PropertySource("classpath:modules/exmo.properties"),
        @PropertySource("classpath:modules/auth.properties")
})
public class ExmoConfig {

    @Bean
    PropertySourcesPlaceholderConfigurer exmoPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
