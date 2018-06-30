package by.minsk;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:modules/exmo.properties")
public class ExmoConfig {

    @Bean
    PropertySourcesPlaceholderConfigurer exmoPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
