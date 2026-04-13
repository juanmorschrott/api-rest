import { useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { hotelService } from '../services/HotelService';
import type { Hotel } from '../models/Hotel';

export default function EditHotel() {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const { register, handleSubmit, reset } = useForm<Hotel>();

  useEffect(() => {
    if (!id) {
      alert('Invalid action.');
      navigate('/');
      return;
    }

    const fetchHotel = async () => {
      try {
        const hotel = await hotelService.getHotelById(Number(id));
        reset(hotel);
      } catch (error) {
        console.error('Error fetching hotel:', error);
        alert('Error fetching hotel');
        navigate('/');
      }
    };

    fetchHotel();
  }, [id, navigate, reset]);

  const onSubmit = async (data: Hotel) => {
    try {
      await hotelService.updateHotel(data);
      navigate('/');
    } catch (error) {
      console.error('Error updating hotel:', error);
      const errorMessage = error instanceof Error ? error.message : 'Unknown error';
      alert(errorMessage);
    }
  };

  const cancel = () => {
    navigate('/');
  };

  return (
    <div className="container py-3">
      <div className="row justify-content-center">
        <div className="col-12 col-md-8 col-lg-6">
          <h2 className="text-center">Edit Hotel</h2>
          <form onSubmit={handleSubmit(onSubmit)}>
            <div className="form-group mb-2">
              <label htmlFor="name">Name:</label>
              <input 
                {...register('name', { required: true })} 
                placeholder="Name" 
                className="form-control" 
                id="name" 
              />
            </div>
            <div className="form-group mb-2">
              <label htmlFor="address">Address:</label>
              <input 
                {...register('address', { required: true })} 
                placeholder="Address" 
                className="form-control" 
                id="address" 
              />
            </div>
            <div className="form-group mb-3">
              <label htmlFor="latitude">Latitude:</label>
              <input 
                type="number"
                step="0.0000001"
                {...register('latitude', { required: true, valueAsNumber: true })} 
                placeholder="Latitude" 
                className="form-control" 
                id="latitude" 
              />
            </div>
            <div className="form-group mb-3">
              <label htmlFor="longitude">Longitude:</label>
              <input 
                type="number"
                step="0.0000001"
                {...register('longitude', { required: true, valueAsNumber: true })} 
                placeholder="Longitude" 
                className="form-control" 
                id="longitude" 
              />
            </div>
            <button type="submit" className="btn btn-primary me-2">Update</button>
            <button type="button" className="btn btn-secondary" onClick={cancel}>Cancel</button>
          </form>
        </div>
      </div>
    </div>
  );
}
