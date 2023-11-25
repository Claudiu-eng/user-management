package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    @Value("${database.ip}")
    private String databaseUrlFlyway;
    @Value("${database.port}")
    private String databasePortFlyway;
    @Value("${database.name}")
    private String databaseName;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + databaseUrlFlyway+":"+ databasePortFlyway +"/"+databaseName);
        config.setUsername("root");
        config.setPassword("12345678");
        return new HikariDataSource(config);
    }

}
