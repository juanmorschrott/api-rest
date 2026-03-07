import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { hotelService } from '../services/HotelService';

interface FormData {
  name: string;
  description: string;
  price: number;
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
            <button type="submit" className="btn btn-primary me-2">Add hotel</button>
            <button type="button" className="btn btn-secondary" onClick={cancel}>Cancel</button>
          </form>
        </div>
      </div>
    </div>
  );
}
