import { Route, Routes } from 'react-router-dom';
import './App.css';
import Allproducts from './components/Allproducts';
import Home from './components/Home';
import Signup from './components/Signup';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';
import { PrivateRoutes } from './components/PrivateRoutes';
import Login from './components/Login';
import Productform from './components/Productform';
import Admin_users from './components/Admin_users';
import Admin_prods from './components/Admin_prods';
import Cart from './components/Cart';
import Admin_orders from './components/Admin_orders';
import Order from './components/Order';
import Verifyotp from './components/Verifyotp';
import UserAccount from './components/UserAccount';

function App() {
  return (
    <div >
      <ToastContainer/>
      <Routes>
        <Route path='/' element={<Home/>}  exact/>

        <Route path='/user' element={<PrivateRoutes/>} exact>
          <Route path='Admin' element={<Productform/>} exact/>
            <Route path='users' element={<Admin_users/>} exact/>
            <Route path='products' element={<Admin_prods/>} exact/>
            <Route path='orders' element={<Admin_orders/>}/>
            <Route path='userorders' element={<Order/>} exact/>
            
            <Route path='addprod' element={<Productform/>} exact/>
            <Route path='verifyotp' element={<Verifyotp/>}/>
            <Route path='account' element={<UserAccount/>}/>
             
        </Route>
        <Route path='/login' element={<Login/>} exact/>
         <Route path='/signup' element={<Signup/>} exact/>
         <Route path='/cart' element={<Cart/>} exact/>
      </Routes>
      
    </div>
  );
}

export default App;
