package com.juanmorschrott.api.reservation.internal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.juanmorschrott.api.reservation.ReservationDto;
import com.juanmorschrott.api.reservation.ReservationService;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReservationService reservationService;

    @Test
    public void whenFindId_thenReturnReservation() throws Exception {
        // given
        ReservationDto dto = ReservationFixtures.reservationDto;

        // when
        when(reservationService.get(1L)).thenReturn(dto);

        // then
        mockMvc.perform(get("/api/v1/reservations/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationId", Matchers.is(1)))
                .andExpect(jsonPath("$.hotelId", Matchers.is(1)))
                .andExpect(jsonPath("$.roomTypeId", Matchers.is(1)))
                .andExpect(jsonPath("$.status", Matchers.is("CONFIRMED")));
    }

    @Test
    public void whenReservationNotFound_thenReturn404() throws Exception {
        // when
        when(reservationService.get(404L)).thenThrow(ReservationNotFoundException.class);

        // then
        mockMvc.perform(get("/api/v1/reservations/404").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
