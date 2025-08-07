import { Component, OnInit } from '@angular/core';
import { Hotel } from '../model/hotel.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HotelService } from '../service/hotel.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-edit-hotel',
  templateUrl: './edit-hotel.component.html',
  styleUrls: ['./edit-hotel.component.css']
})
export class EditHotelComponent implements OnInit {

  hotel?: Hotel;
  editForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private hotelService: HotelService) {
    this.editForm = formBuilder.group({
      name: formBuilder.control('', Validators.required),
      description: formBuilder.control('', Validators.required),
      price: formBuilder.control('', Validators.required)
    });
  }

  ngOnInit() {
    let hotelId = +localStorage!.getItem('editHotelId')!;
    if (!hotelId) {
      alert('Invalid action.');
      this.router.navigate(['list-hotel']);
      return;
    }
    this.editForm = this.formBuilder.group({
      id: [],
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', Validators.required]
    });
    this.hotelService.getHotelById(hotelId)
      .subscribe(data => {
        this.editForm.setValue(data);
      });
  }

  onSubmit() {
    this.hotelService.updateHotel(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(['list-hotel']);
        },
        error => {
          alert(error);
        });
  }

  cancel() {
    this.router.navigate(['list-hotel']);
  }

}
