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
              <label htmlFor="description">Description:</label>
              <input 
                {...register('description', { required: true })} 
                placeholder="Description" 
                className="form-control" 
                id="description" 
              />
            </div>
            <div className="form-group mb-3">
              <label htmlFor="price">Price:</label>
              <input 
                type="number"
                step="0.01"
                {...register('price', { required: true, valueAsNumber: true })} 
                placeholder="Mean Room Price" 
                className="form-control" 
                id="price" 
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
