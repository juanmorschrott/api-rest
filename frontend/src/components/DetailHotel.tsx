import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { hotelService } from '../services/HotelService';
import type { Hotel } from '../models/Hotel';

export default function DetailHotel() {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [hotel, setHotel] = useState<Hotel | null>(null);

  useEffect(() => {
    if (!id) {
      alert('Invalid action.');
      navigate('/');
      return;
    }

    const fetchDetail = async () => {
      try {
        const data = await hotelService.getHotelById(Number(id));
        setHotel(data);
      } catch (error) {
        console.error('Error fetching hotel:', error);
        alert('Error fetching hotel');
        navigate('/');
      }
    };

    fetchDetail();
  }, [id, navigate]);

  const list = () => {
    navigate('/');
  };

  if (!hotel) {
    return <div className="text-center py-5">Loading...</div>;
  }

  return (
    <div className="container py-3">
      <h2 className="text-center">Hotel Details</h2>
      <hr />
      <div className="row justify-content-center">
        <div className="col-12 col-md-8 col-lg-6">
          <div className="mb-2">
            <label><b>Name: </b></label> {hotel.name}
          </div>
          <div className="mb-2">
            <label><b>Address: </b></label> {hotel.address}
          </div>
          <div className="mb-2">
            <label><b>Latitude: </b></label> {hotel.latitude}
          </div>
          <div className="mb-2">
            <label><b>Longitude: </b></label> {hotel.longitude}
          </div>
          <button onClick={list} className="btn btn-primary mt-3">Back to Hotels List</button>
        </div>
      </div>
    </div>
  );
}
