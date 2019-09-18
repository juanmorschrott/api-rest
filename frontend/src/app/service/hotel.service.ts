import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Hotel} from '../model/hotel.model';

@Injectable()
export class HotelService {
  constructor(private http: HttpClient) { }
  baseUrl = 'http://localhost:8080/api/v1/hotels/';

  getHotels() {
    return this.http.get<Hotel[]>(this.baseUrl);
  }

  getHotelById(id: string) {
    return this.http.get(this.baseUrl + id);
  }

  createHotel(hotel: Hotel) {
    return this.http.post(this.baseUrl, hotel);
  }

  updateHotel(hotel: Hotel) {
    return this.http.put(this.baseUrl, hotel);
  }

  deleteHotel(id: number) {
    return this.http.delete(this.baseUrl + id);
  }
}
