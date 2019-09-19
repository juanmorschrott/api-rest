import {Component, OnInit} from '@angular/core';
import {Hotel} from '../model/hotel.model';
import {Router} from '@angular/router';
import {HotelService} from '../service/hotel.service';

@Component({
  selector: 'app-list-hotel',
  templateUrl: './list-hotel.component.html',
  styleUrls: ['./list-hotel.component.css']
})
export class ListHotelComponent implements OnInit {

  hotels: Hotel[];

  constructor(private router: Router, private hotelService: HotelService) {
  }

  ngOnInit() {
    this.hotelService.getHotels()
      .subscribe(data => {
        console.log(data);
        this.hotels = data;
      });
  }

  deleteHotel(hotel: Hotel): void {
    this.hotelService.deleteHotel(hotel.id)
      .subscribe(data => {
        this.hotels = this.hotels.filter(u => u !== hotel);
      });
  }

  editHotel(hotel: Hotel): void {
    localStorage.removeItem('editHotelId');
    localStorage.setItem('editHotelId', hotel.id.toString());
    this.router.navigate(['edit-hotel']);
  }

  addHotel(): void {
    this.router.navigate(['add-hotel']);
  }

  hotelDetails(hotel: Hotel) {
    localStorage.removeItem('hotelDetailId');
    localStorage.setItem('hotelDetailId', hotel.id.toString());
    this.router.navigate(['hotel-detail']);
  }

}
