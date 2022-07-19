import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Hotel } from '../model/hotel.model';

@Injectable()
export class HotelService {
  
  url = `${environment.api_url}/hotels`;
  slash = '/'

  constructor(private http: HttpClient) {}

  getHotels() {
    return this.http.get<Hotel[]>(this.url);
  }

  getHotelById(id: number) {
    return this.http.get(this.url + this.slash + id);
  }

  createHotel(hotel: Hotel) {
    return this.http.post(this.url, hotel);
  }

  updateHotel(hotel: Hotel) {
    return this.http.put(this.url, hotel);
  }

  deleteHotel(id: number) {
    return this.http.delete(this.url + this.slash + id);
  }
}
