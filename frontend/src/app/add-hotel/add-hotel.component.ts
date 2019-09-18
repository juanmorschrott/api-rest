import { Component, OnInit } from '@angular/core';
import { HotelService } from '../service/hotel.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-hotel',
  templateUrl: './add-hotel.component.html',
  styleUrls: ['./add-hotel.component.css']
})
export class AddHotelComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private router: Router, private hotelService: HotelService) { }

  addForm: FormGroup;

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      id: [],
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', Validators.required]
    });
  }

  onSubmit() {
    this.hotelService.createHotel(this.addForm.value)
      .subscribe( data => {
        this.router.navigate(['list-hotel']);
      });
  }

}
