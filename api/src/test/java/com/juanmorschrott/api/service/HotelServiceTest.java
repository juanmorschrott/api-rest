package com.juanmorschrott.api.service;

import com.juanmorschrott.api.exception.HotelNotFoundException;
import com.juanmorschrott.api.fixtures.HotelFixtures;
import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.repository.HotelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Optional.of;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    @InjectMocks
    private HotelService hotelService;

    @Mock
    private HotelRepository hotelRepository;

    @Test
    public void whenList_thenShouldReturnAllValues() {
        // when
        when(hotelRepository.findAll()).thenReturn(HotelFixtures.getHotelsArray());
        List<Hotel> hotels = hotelService.list();

        // then
        assertThat(hotels.get(0).getName()).isEqualTo(HotelFixtures.getHotelsArray().get(0).getName());
        assertThat(hotels.size()).isEqualTo(3);
    }

    @Test
    public void whenFindWithValidId_thenHotelShouldBeFound() {
        // when
        Hotel hotel = HotelFixtures.hotel;

        when(hotelRepository.findById(anyLong())).thenReturn(of(hotel));
        Hotel found = hotelService.get(1L);

        // then
        assertThat(found.getId()).isEqualTo(1);
    }

    @Test
    public void whenFindWithValidName_thenHotelShouldBeFound() {
        // given
        Hotel hotel = HotelFixtures.hotel;
        String name = "Foo";

        // when
        when(hotelRepository.findByName(anyString())).thenReturn(of(hotel));
        Hotel found = hotelService.getByName(name);

        // then
        assertThat(found.getName()).isEqualTo(name);
    }

    @Test
    public void whenNotValidName_thenHotelShouldThrowNotFoundException() {
        // given
        String name = "Foo";

        // when
        HotelNotFoundException thrown = Assertions.assertThrows(HotelNotFoundException.class, () -> {
            Hotel hotel = hotelService.getByName(name);
        });
    }

}
