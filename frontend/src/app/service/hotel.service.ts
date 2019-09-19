import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Hotel } from '../model/hotel.model';

@Injectable()
export class HotelService {
  
  constructor(private http: HttpClient) { }

  uri = 'http://localhost:8080/api/v1/hotels/';

  getHotels() {
    return this.http.get<Hotel[]>(this.uri);
  }

  getHotelById(id: number) {
    return this.http.get(this.uri + id);
  }

  createHotel(hotel: Hotel) {
    return this.http.post(this.uri, hotel);
  }

  updateHotel(hotel: Hotel) {
    return this.http.put(this.uri, hotel);
  }

  deleteHotel(id: number) {
    return this.http.delete(this.uri + id);
  }
}
