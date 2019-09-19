package com.juanmorschrott.api.controller;

import com.juanmorschrott.api.model.Hotel;
import com.juanmorschrott.api.service.HotelServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Integration test using TestRestTemplate
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelControllerIntegrationTest {

    @MockBean
    private HotelServiceImpl hotelService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenList_thenReturnHotels() throws Exception {
        // given
        given(hotelService.list()).willReturn(getHotelsArray());

        // when
        ResponseEntity<List<Hotel>> response = restTemplate.exchange(
                "/api/v1/hotels",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Hotel>>(){});

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size() == 3);
    }

    private List<Hotel> getHotelsArray() {
        return Arrays.asList(new Hotel(1, "Foo", "Test Description", BigDecimal.valueOf(99.9)),
                new Hotel(2, "Foo2", "Test Description", BigDecimal.valueOf(99.9)),
                new Hotel(3, "Foo3", "Test Description", BigDecimal.valueOf(99.9)));
    }

    @Test
    public void whenReceiveHotel_thenCreateHotel() throws Exception {
        // given
        Hotel hotel = new Hotel(1, "Foo", "Test Description", BigDecimal.valueOf(99.9));
        given(hotelService.create(hotel)).willReturn(hotel);

        // when
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Hotel> requestEntity = new HttpEntity<>(hotel, requestHeaders);
        ResponseEntity<Hotel> response = restTemplate.exchange(
                "/api/v1/hotels",
                HttpMethod.POST,
                requestEntity,
                Hotel.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(hotel);
    }

    @Test
    public void whenUpdate_thenReturnUpdatedHotel() throws Exception {
        // given
        Hotel hotel = new Hotel(1, "Foo", "Test Description", BigDecimal.valueOf(99.9));
        Hotel updatedHotel = new Hotel(1, "Updated Foo", "Test Description", BigDecimal.valueOf(99.9));
        given(hotelService.update(hotel)).willReturn(updatedHotel);

        // when
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Hotel> requestEntity = new HttpEntity<>(hotel, requestHeaders);
        ResponseEntity<Hotel> response = restTemplate.exchange(
                "/api/v1/hotels",
                HttpMethod.PUT,
                requestEntity,
                Hotel.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName().equals(updatedHotel.getName()));
    }

}
