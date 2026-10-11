package com.juanmorschrott.api.hotel.internal;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HotelFlywayConfig {

    @Bean(initMethod = "migrate")
    Flyway hotelFlyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .schemas("hotel")
                .locations("classpath:db/migration/hotel")
                .load();
    }
}
