package com.juanmorschrott.api.hotel;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Integration test using TestRestTemplate
 */
@Testcontainers
@AutoConfigureTestRestTemplate
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelControllerIntegrationTest {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.36");


    @MockitoBean
    private HotelService hotelService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenList_thenReturnHotels() {
        // given
        given(hotelService.list()).willReturn(HotelFixtures.getHotelsArray());

        // when
        ResponseEntity<List<Hotel>> response = restTemplate.exchange(
                "/api/v1/hotels",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(3);
    }

    @Test
    public void whenReceiveHotel_thenCreateHotel() {
        // given
        HotelDto hotelDto = HotelFixtures.hotelDto;
        Hotel hotel = HotelFixtures.hotel;

        given(hotelService.create(hotelDto)).willReturn(hotel);

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
    public void whenUpdate_thenUpdatedHotel() {
        // given
        HotelDto hotelDto = HotelFixtures.hotelDto;
        Hotel updatedHotel = new Hotel(1, "Updated Foo", "Test Description", BigDecimal.valueOf(99.9));
        given(hotelService.update(hotelDto)).willReturn(updatedHotel);

        // when
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<HotelDto> requestEntity = new HttpEntity<>(hotelDto, requestHeaders);

        ResponseEntity<Hotel> response = restTemplate.exchange(
                "/api/v1/hotels",
                HttpMethod.PUT,
                requestEntity,
                Hotel.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(updatedHotel.getName());
    }

}
