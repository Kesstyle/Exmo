package src.by.minsk.kes.exmo.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import src.by.minsk.kes.exmo.legacy.ExmoRestAdapter;

@Configuration
@ComponentScan(basePackages = "src.by.minsk.kes.exmo")
public class AppConfig {

  @Bean
  public ExmoRestAdapter exmoRestAdapter() {
    final ExmoRestAdapter e = new ExmoRestAdapter("key", "secret");
    return e;
  }
}
