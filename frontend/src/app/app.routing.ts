import { RouterModule, Routes } from '@angular/router';
import { AddHotelComponent } from './add-hotel/add-hotel.component';
import { ListHotelComponent } from './list-hotel/list-hotel.component';
import { EditHotelComponent } from './edit-hotel/edit-hotel.component';

const routes: Routes = [
  { path: 'add-hotel', component: AddHotelComponent },
  { path: 'list-hotel', component: ListHotelComponent },
  { path: 'edit-hotel', component: EditHotelComponent },
  {path : '', component : ListHotelComponent}
];

export const routing = RouterModule.forRoot(routes);
