package com.juanmorschrott.api.hotel.internal;

import com.juanmorschrott.api.hotel.HotelDto;
import com.juanmorschrott.api.hotel.HotelService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HotelService hotelService;

    @Test
    public void whenFindId_thenReturnHotel() throws Exception {
        // given
        HotelDto hotelDto = HotelFixtures.hotelDto;

        // when
        when(hotelService.get(1L)).thenReturn(hotelDto);

        // then
        mockMvc.perform(get("/api/v1/hotels/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Foo")));
    }

    @Test
    public void whenHotelNotFound_thenReturn404() throws Exception {
        // when
        Long hotelId = 404L;
        when(hotelService.get(hotelId)).thenThrow(HotelNotFoundException.class);

        // then
        mockMvc.perform(get("/api/v1/hotels/404").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
