import { Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import ListHotel from './components/ListHotel';
import AddHotel from './components/AddHotel';
import EditHotel from './components/EditHotel';
import DetailHotel from './components/DetailHotel';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<ListHotel />} />
        <Route path="list-hotel" element={<ListHotel />} />
        <Route path="add-hotel" element={<AddHotel />} />
        <Route path="edit-hotel/:id" element={<EditHotel />} />
        <Route path="hotel-detail/:id" element={<DetailHotel />} />
      </Route>
    </Routes>
  );
}

export default App;
