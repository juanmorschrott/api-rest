package com.juanmorschrott.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.service.HotelServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Integration test with Spring Application Context using MockMvc
 */
@RunWith(SpringRunner.class)
@WebMvcTest(HotelController.class)
public class HotelControllerWithContextIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HotelServiceImpl hotelService;

    private JacksonTester<Hotel> jacksonTester;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void whenFindByName_thenReturnHotel() throws Exception {
        // given
        Hotel hotel = new Hotel(1, "Foo", "Test Description", BigDecimal.valueOf(99.9));
        given(hotelService.get(1L)).willReturn(hotel);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/hotels/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jacksonTester.write(
                        new Hotel(1, "Foo", "Test Description", BigDecimal.valueOf(99.9))).getJson()
        );
    }

    @Test
    public void whenFindByName_thenReturnEmptyStringIfNotExists() throws Exception {
        // given
        given(hotelService.get(1L)).willReturn(null);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/hotels/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEmpty();
    }

}
