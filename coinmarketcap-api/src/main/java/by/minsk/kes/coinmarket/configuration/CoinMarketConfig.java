package by.minsk.kes.coinmarket.configuration;

import by.minsk.kes.coinmarket.env.configuration.CoinMarketPropertiesConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "by.minsk.kes.coinmarket")
@Import({CoinMarketPropertiesConfig.class})
@EnableMBeanExport
public class CoinMarketConfig {
}
