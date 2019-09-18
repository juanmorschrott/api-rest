import { RouterModule, Routes } from '@angular/router';
import { AddHotelComponent } from './add-hotel/add-hotel.component';
import { ListHotelComponent } from './list-hotel/list-hotel.component';
import { EditHotelComponent } from './edit-hotel/edit-hotel.component';
import { HotelDetailComponent } from './hotel-detail/hotel-detail.component';

const routes: Routes = [
  { path: 'add-hotel', component: AddHotelComponent },
  { path: 'list-hotel', component: ListHotelComponent },
  { path: 'edit-hotel', component: EditHotelComponent },
  { path: 'hotel-detail', component: HotelDetailComponent },
  { path : '', component : ListHotelComponent}
];

export const routing = RouterModule.forRoot(routes);
