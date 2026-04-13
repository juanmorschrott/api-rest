package com.juanmorschrott.api.hotel;

import com.juanmorschrott.api.hotel.internal.Hotel;
import com.juanmorschrott.api.hotel.internal.HotelNotFoundException;
import com.juanmorschrott.api.hotel.internal.HotelRepository;
import com.juanmorschrott.api.hotel.internal.HotelFixtures;
import com.juanmorschrott.api.hotel.internal.HotelMapper;

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

    @Mock
    private HotelMapper mapper;

    @Test
    public void whenList_thenShouldReturnAllValues() {
        // given
        List<Hotel> entityList = HotelFixtures.getHotelsArray();
        List<HotelDto> dtoList = HotelFixtures.getHotelsDtoList();

        // when
        when(hotelRepository.findAll()).thenReturn(entityList);
        when(mapper.toDtoList(entityList)).thenReturn(dtoList);

        List<HotelDto> hotels = hotelService.list();

        // then
        assertThat(hotels.get(0).name()).isEqualTo(dtoList.get(0).name());
        assertThat(hotels.size()).isEqualTo(3);
    }

    @Test
    public void whenFindWithValidId_thenHotelShouldBeFound() {
        // given
        Hotel hotel = HotelFixtures.hotel;
        HotelDto hotelDto = HotelFixtures.hotelDto;

        // when
        when(hotelRepository.findById(anyLong())).thenReturn(of(hotel));
        when(mapper.toDto(hotel)).thenReturn(hotelDto);

        HotelDto found = hotelService.get(1L);

        // then
        assertThat(found.hotelId()).isEqualTo(1L);
    }

    @Test
    public void whenFindWithValidName_thenHotelShouldBeFound() {
        // given
        Hotel hotel = HotelFixtures.hotel;
        HotelDto hotelDto = HotelFixtures.hotelDto;
        String name = "Atenea";

        // when
        when(hotelRepository.findByName(anyString())).thenReturn(of(hotel));
        when(mapper.toDto(hotel)).thenReturn(hotelDto);

        HotelDto found = hotelService.getByName(name);

        // then
        assertThat(found.name()).isEqualTo(name);
    }

    @Test
    public void whenNotValidName_thenHotelShouldThrowNotFoundException() {
        // given
        String name = "Foo";

        // when
        HotelNotFoundException thrown = Assertions.assertThrows(HotelNotFoundException.class, () -> {
            hotelService.getByName(name);
        });

        assertThat(thrown).isNotNull();
    }

}
