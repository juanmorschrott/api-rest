import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { hotelService } from '../services/HotelService';

interface FormData {
  name: string;
  address: string;
  latitude: number;
  longitude: number;
}

export default function AddHotel() {
  const navigate = useNavigate();
  const { register, handleSubmit } = useForm<FormData>();

  const onSubmit = async (data: FormData) => {
    try {
      await hotelService.createHotel(data);
      navigate('/');
    } catch (error) {
      console.error('Error adding hotel:', error);
    }
  };

  const cancel = () => {
    navigate('/');
  };

  return (
    <div className="container py-3">
      <div className="row justify-content-center">
        <div className="col-12 col-md-8 col-lg-6">
          <h2 className="text-center">Add Hotel</h2>
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
            <button type="submit" className="btn btn-primary me-2">Add hotel</button>
            <button type="button" className="btn btn-secondary" onClick={cancel}>Cancel</button>
          </form>
        </div>
      </div>
    </div>
  );
}
