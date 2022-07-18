package com.juanmorschrott.api.service;

import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HotelServiceImplIntegrationTest {

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Mock
    private HotelRepository hotelRepository;

    private Hotel hotel = null;

    @BeforeEach
    public void setUp() {
        hotel = new Hotel(1, "Test Name", "Test Description", BigDecimal.valueOf(99.9));
    }

    private List<Hotel> getHotelsArray() {
        return Arrays.asList(new Hotel(1, "Foo", "Test Description", BigDecimal.valueOf(99.9)),
                new Hotel(2, "Foo2", "Test Description", BigDecimal.valueOf(99.9)),
                new Hotel(3, "Foo3", "Test Description", BigDecimal.valueOf(99.9)));
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
        assertThat(hotel).isNull();
    }

}
