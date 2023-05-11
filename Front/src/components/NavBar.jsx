import { Link } from 'react-router-dom';
import '../styles.css';

export const NavBar = () => {
  return (
    <nav className='navbar'>
    
    <Link to="/"> 
      <img 
        src='./images/pet-house.png'  
        alt="icono"
        className="logo"
      /> 
      <p className='slogan-navbar'> Los alojamos como en casa </p>
    </Link> 
    
    <div className='navbar-buttons'>
      <button className='add-acount-button'>
        <Link to='/register'>Sign In</Link>
      </button>
      <button className='start-session-button'>Login</button>
    </div>
    
    </nav>
    
  )
}
