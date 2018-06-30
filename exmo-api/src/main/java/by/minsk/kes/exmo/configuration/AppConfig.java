package by.minsk.kes.exmo.configuration;

import by.minsk.ExmoConfig;
import by.minsk.kes.exmo.legacy.ExmoRestAdapter;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = "by.minsk")
@Import(ExmoConfig.class)
@EnableMBeanExport
public class AppConfig {

  @Bean
  public ExmoRestAdapter exmoRestAdapter() {
    final ExmoRestAdapter e = new ExmoRestAdapter("key", "secret");
    return e;
  }
}
