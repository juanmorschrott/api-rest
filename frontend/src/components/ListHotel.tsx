import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import type { Hotel } from '../models/Hotel';
import { hotelService } from '../services/HotelService';

export default function ListHotel() {
  const [hotels, setHotels] = useState<Hotel[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    loadHotels();
  }, []);

  const loadHotels = async () => {
    try {
      const data = await hotelService.getHotels();
      setHotels(data);
    } catch (error) {
      console.error('Error fetching hotels:', error);
    }
  };

  const deleteHotel = async (hotel: Hotel) => {
    try {
      await hotelService.deleteHotel(hotel.id);
      setHotels(hotels.filter(h => h.id !== hotel.id));
    } catch (error) {
      console.error('Error deleting hotel:', error);
    }
  };

  const editHotel = (hotel: Hotel) => {
    navigate(`/edit-hotel/${hotel.id}`);
  };

  const hotelDetails = (hotel: Hotel) => {
    navigate(`/hotel-detail/${hotel.id}`);
  };

  const addHotel = () => {
    navigate('/add-hotel');
  };

  return (
    <div id="list-hotel" className="container-fluid">
      <h2>Hotel Details</h2>
      <hr />
      <button className="btn btn-primary" onClick={addHotel}>Add Hotel</button>
      <hr />
      <div className="table-responsive">
        <table className="table table-striped align-middle">
          <thead>
            <tr>
              <th className="d-none">Id</th>
              <th>Name</th>
              <th>Description</th>
              <th>Mean Room Price</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {hotels.map(hotel => (
              <tr key={hotel.id}>
                <td className="d-none">{hotel.id}</td>
                <td>{hotel.name}</td>
                <td>{hotel.description}</td>
                <td>{new Intl.NumberFormat('en-US', { style: 'currency', currency: 'EUR' }).format(hotel.price)}</td>
                <td>
                  <button className="btn btn-warning me-2" onClick={() => deleteHotel(hotel)}>Delete</button>
                  <button className="btn btn-secondary me-2" onClick={() => editHotel(hotel)}>Edit</button>
                  <button className="btn btn-primary me-2" onClick={() => hotelDetails(hotel)}>Details</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
