package com.juanmorschrott.api.controller;

import com.juanmorschrott.api.exception.HotelNotFoundException;
import com.juanmorschrott.api.fixtures.HotelFixtures;
import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.service.HotelService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test with Spring Application Context using MockMvc
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    @Test
    public void whenFindId_thenReturnHotel() throws Exception {
        // given
        Hotel hotel = HotelFixtures.hotel;

        // when
        when(hotelService.get(1L)).thenReturn(hotel);

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
