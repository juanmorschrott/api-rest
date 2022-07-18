package com.juanmorschrott.api.repository;

import com.juanmorschrott.api.model.Hotel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class HotelRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

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
