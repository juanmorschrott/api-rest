package com.juanmorschrott.api.hotel.internal;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
public class HotelRepositoryTest {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.36");

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void whenFindById_thenReturnHotel() {
        // given
        Hotel hotel = new Hotel(
            null,
            "Test Name",
            "Calle Gran Via 1, Madrid",
            BigDecimal.valueOf(40.4168000),
            BigDecimal.valueOf(-3.7037900)
        );
        entityManager.persist(hotel);
        entityManager.flush();

        // when
        Optional<Hotel> found = hotelRepository.findById(hotel.getHotelId());

        // then
        assertThat(found.get().getHotelId()).isEqualTo(hotel.getHotelId());
    }

    @Test
    public void whenFindByName_thenReturnHotel() {
        // given
        Hotel hotel = new Hotel(
            null,
            "Test Name",
            "Calle Gran Via 1, Madrid",
            BigDecimal.valueOf(40.4168000),
            BigDecimal.valueOf(-3.7037900)
        );
        entityManager.persist(hotel);
        entityManager.flush();

        // when
        Optional<Hotel> found = hotelRepository.findByName(hotel.getName());

        // then
        assertThat(found.get().getName()).isEqualTo(hotel.getName());
    }

}
