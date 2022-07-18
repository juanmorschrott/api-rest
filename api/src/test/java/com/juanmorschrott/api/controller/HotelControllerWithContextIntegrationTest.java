package com.juanmorschrott.api.controller;

import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.service.HotelService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test with Spring Application Context using MockMvc
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(HotelController.class)
public class HotelControllerWithContextIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    @Test
    public void whenFindByName_thenReturnHotel() throws Exception {
        // given
        Hotel hotel = Hotel.builder()
                .id(1)
                .name("Foo")
                .description("Test Description")
                .price(BigDecimal.valueOf(99.9))
                .build();

        // when
        when(hotelService.get(1L)).thenReturn(hotel);

        // then
        mockMvc.perform(get("/api/v1/hotels/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Foo")));
    }

    @Test
    public void whenFindByName_thenReturnEmptyStringIfNotExists() throws Exception {
        // when
        when(hotelService.get(anyLong())).thenReturn(null);

        // then
        mockMvc.perform(get("/api/v1/hotels/404"))
                .andExpect(status().isNotFound());
    }

}
