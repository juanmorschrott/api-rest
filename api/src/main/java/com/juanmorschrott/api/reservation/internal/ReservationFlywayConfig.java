package com.juanmorschrott.api.reservation.internal;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ReservationFlywayConfig {

    @Bean(initMethod = "migrate")
    Flyway reservationFlyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .schemas("reservation")
                .locations("classpath:db/migration/reservation")
                .load();
    }
}
