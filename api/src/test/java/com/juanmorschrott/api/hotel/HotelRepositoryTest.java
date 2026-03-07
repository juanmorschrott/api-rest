package com.juanmorschrott.api.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import jakarta.persistence.EntityManager;
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
        Hotel hotel = new Hotel(1, "Test Name", "Test Description", BigDecimal.valueOf(99.9));
        entityManager.merge(hotel);
        entityManager.flush();

        // when
        Optional<Hotel> found = hotelRepository.findById(hotel.getId());

        // then
        assertThat(found.get().getId()).isEqualTo(hotel.getId());
    }

    @Test
    public void whenFindByName_thenReturnHotel() {
        // given
        Hotel hotel = new Hotel(1, "Test Name", "Test Description", BigDecimal.valueOf(99.9));
        entityManager.merge(hotel);
        entityManager.flush();

        // when
        Optional<Hotel> found = hotelRepository.findByName(hotel.getName());

        // then
        assertThat(found.get().getName()).isEqualTo(hotel.getName());
    }

}
