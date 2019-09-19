import {HotelService} from './hotel.service';
import {Hotel} from '../model/hotel.model';
import {asyncData} from './async-observable-helpers';

fdescribe('ValueService', () => {
  let hotelService: HotelService;
  let httpClientSpy: { get: jasmine.Spy };
  const expectedHotels: Hotel[] = [{'id': 1, 'name': 'Atenea', 'description': 'Hotel económico y confortable', 'price': 35},
      {'id': 2, 'name': 'Kontiki', 'description': 'Gran calidad', 'price': 105},
      {'id': 3, 'name': 'Apollo', 'description': 'Hotel económico y confortable', 'price': 35}];

  beforeEach(() => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
    hotelService = new HotelService(httpClientSpy as any);
  });

  it('should be created', () => {
    expect(hotelService).toBeTruthy();
  });

  it('should get a list of hotels', () => {  
    httpClientSpy.get.and.returnValue(asyncData(expectedHotels));

    hotelService.getHotels().subscribe(hotels => {
        expect(hotels).toEqual(expectedHotels, 'expected hotels');
      }, fail
    );
    expect(httpClientSpy.get.calls.count()).toBe(1, 'one call');
  });

  it('should get hotel by id', () => {
    const expectedHotel: Hotel = expectedHotels[0];

    httpClientSpy.get.and.returnValue(asyncData(expectedHotel));

    hotelService.getHotelById(1).subscribe(hotel => {
        expect(hotel).toEqual(expectedHotel, 'expected hotel');
      }, fail
    );
    expect(httpClientSpy.get.calls.count()).toBe(1, 'one call');
  });

});
