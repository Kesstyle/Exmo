package by.minsk.kes.coinmarket.configuration;

import by.minsk.configuration.CoinMarketCapConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "by.minsk")
@Import(CoinMarketCapConfig.class)
@EnableMBeanExport
public class CoinMarketConfig {
}
