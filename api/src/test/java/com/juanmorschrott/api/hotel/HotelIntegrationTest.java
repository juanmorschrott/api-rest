package com.juanmorschrott.api.hotel;

import com.juanmorschrott.api.hotel.internal.HotelFixtures;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Integration test using TestRestTemplate
 */
@Testcontainers
@AutoConfigureTestRestTemplate
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelIntegrationTest {

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
        given(hotelService.list()).willReturn(HotelFixtures.getHotelsDtoList());

        // when
        ResponseEntity<List<HotelDto>> response = restTemplate.exchange(
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

        given(hotelService.create(hotelDto)).willReturn(hotelDto);

        // when
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<HotelDto> requestEntity = new HttpEntity<>(hotelDto, requestHeaders);

        ResponseEntity<HotelDto> response = restTemplate.exchange(
                "/api/v1/hotels",
                HttpMethod.POST,
                requestEntity,
                HotelDto.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(hotelDto);
    }

    @Test
    public void whenUpdate_thenUpdatedHotel() {
        // given
        HotelDto hotelDto = HotelFixtures.hotelDto;
        HotelDto updatedHotelDto = new HotelDto(1L, "Updated Hotel", "456 Updated St", 40.7128, -74.0060);
        given(hotelService.update(1L, hotelDto)).willReturn(updatedHotelDto);

        // when
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<HotelDto> requestEntity = new HttpEntity<>(hotelDto, requestHeaders);

        ResponseEntity<HotelDto> response = restTemplate.exchange(
                "/api/v1/hotels/1",
                HttpMethod.PUT,
                requestEntity,
                HotelDto.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().name()).isEqualTo(updatedHotelDto.name());
    }

}
