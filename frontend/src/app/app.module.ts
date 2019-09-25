import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { routing } from './app.routing';
import { AddHotelComponent } from './add-hotel/add-hotel.component';
import { EditHotelComponent } from './edit-hotel/edit-hotel.component';
import { ListHotelComponent } from './list-hotel/list-hotel.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HotelService } from './service/hotel.service';
import { DetailHotelComponent } from './detail-hotel/detail-hotel.component';

@NgModule({
  declarations: [
    AppComponent,
    AddHotelComponent,
    EditHotelComponent,
    ListHotelComponent,
    DetailHotelComponent
  ],
  imports: [
    BrowserModule,
    routing,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [HotelService],
  bootstrap: [AppComponent]
})
export class AppModule { }
