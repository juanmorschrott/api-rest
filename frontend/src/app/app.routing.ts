import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AddHotelComponent } from './add-hotel/add-hotel.component';
import { ListHotelComponent } from './list-hotel/list-hotel.component';
import { EditHotelComponent } from './edit-hotel/edit-hotel.component';
import { DetailHotelComponent } from './detail-hotel/detail-hotel.component';

const routes: Routes = [
  { path: 'add-hotel', component: AddHotelComponent },
  { path: 'list-hotel', component: ListHotelComponent },
  { path: 'edit-hotel', component: EditHotelComponent },
  { path: 'hotel-detail', component: DetailHotelComponent },
  { path : '', component : ListHotelComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
