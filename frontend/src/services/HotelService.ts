import axios from 'axios';
import type { Hotel } from '../models/Hotel';

const API_URL = 'http://localhost:8080/api/v1/hotels';

export const hotelService = {
  getHotels: async (): Promise<Hotel[]> => {
    const response = await axios.get(API_URL);
    return response.data;
  },

  getHotelById: async (id: number): Promise<Hotel> => {
    const response = await axios.get(`${API_URL}/${id}`);
    return response.data;
  },

  createHotel: async (hotel: Omit<Hotel, 'hotelId'>): Promise<Hotel> => {
    const response = await axios.post(API_URL, hotel);
    return response.data;
  },

  updateHotel: async (hotel: Hotel): Promise<Hotel> => {
    const response = await axios.put(`${API_URL}/${hotel.hotelId}`, hotel);
    return response.data;
  },

  deleteHotel: async (id: number): Promise<void> => {
    await axios.delete(`${API_URL}/${id}`);
  }
};
