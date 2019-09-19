package com.juanmorschrott.api.service;

import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.repository.HotelRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class HotelServiceImplIntegrationTest {

    @Autowired
    private HotelService hotelService;

    @MockBean
    private HotelRepository hotelRepository;

    private Hotel hotel = null;

    @Before
    public void setUp() {
        hotel = new Hotel(1, "Test Name", "Test Description", BigDecimal.valueOf(99.9));
    }

    private List<Hotel> getHotelsArray() {
        return Arrays.asList(new Hotel(1, "Foo", "Test Description", BigDecimal.valueOf(99.9)),
                new Hotel(2, "Foo2", "Test Description", BigDecimal.valueOf(99.9)),
                new Hotel(3, "Foo3", "Test Description", BigDecimal.valueOf(99.9)));
    }

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public HotelService employeeService() {
            return new HotelServiceImpl();
        }
    }

    @Test
    public void whenList_thenHotelShouldReturnAllValues() {
        // when
        when(hotelRepository.findAll()).thenReturn(getHotelsArray());
        List<Hotel> hotels = hotelService.list();

        // then
        assertThat(hotels.get(0).getName()).isEqualTo(getHotelsArray().get(0).getName());
        assertThat(hotels.size()).isEqualTo(3);
    }

    @Test
    public void whenValidId_thenHotelShouldBeFound() {
        // when
        when(hotelRepository.findById(hotel.getId())).thenReturn(java.util.Optional.of(hotel));
        Hotel found = hotelService.get(1L);

        // then
        assertThat(found.getId()).isEqualTo(1);
    }

    @Test
    public void whenValidName_thenHotelShouldBeFound() {
        // given
        String name = "Test Name";

        // when
        when(hotelRepository.findByName(hotel.getName())).thenReturn(java.util.Optional.of(hotel));
        Hotel found = hotelService.getByName(name);

        // then
        assertThat(found.getName()).isEqualTo(name);
    }

    @Test
    public void whenNotValidName_thenHotelShouldNotBeFound() {
        // given
        String name = "Foo";

        // when
        Hotel hotel = hotelService.getByName(name);

        // then
        assertThat(hotelService.getByName(name)).isNull();
    }

}
