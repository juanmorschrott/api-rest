import { Component, OnInit } from '@angular/core';
import { Hotel } from '../model/hotel.model';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from '../service/hotel.service';

@Component({
  selector: 'app-hotel-detail',
  templateUrl: './hotel-detail.component.html',
  styleUrls: ['./hotel-detail.component.css']
})
export class HotelDetailComponent implements OnInit {

  id: string;
  hotel: any;

  constructor(private route: ActivatedRoute, private router: Router, private hotelService: HotelService) { }

  ngOnInit() {
    this.hotel = new Hotel();
    const hotelId = localStorage.getItem('hotelDetailId');
    if (!hotelId) {
      alert('Invalid action.');
      this.router.navigate(['list-hotel']);
      return;
    }
    
    this.hotelService.getHotelById(hotelId)
      .subscribe(data => {
        console.log(data);
        this.hotel = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['list-hotel']);
  }

}
