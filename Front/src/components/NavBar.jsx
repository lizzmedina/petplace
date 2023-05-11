import { Link } from 'react-router-dom';
import '.././styles.css';

export const NavBar = () => {
  return (
    <nav className = 'navbar'>
    
      <Link className = 'navbar-link-container' to = "/"> 
        <img 
          src='./images/pet-house.png'  
          alt="icono"
          className="logo"
        /> 
        <p className='slogan-navbar'> Los alojamos como en casa </p>
      </Link> 
    
<<<<<<< HEAD
    <div className='navbar-buttons'>
      <button className='add-acount-button'>
        <Link to='/register'>Sign In</Link>
      </button>
      <button className='start-session-button'>Login</button>
    </div>
=======
      <div className ='navbar-buttons'>
        <button className ='add-acount-button'>Crear cuenta</button>
        <button className ='start-session-button'>Iniciar Sesión</button>
      </div>
>>>>>>> 3953e00cda0408e6534b11d54592c3e9193e3c15
    
    </nav>
    
  )
}
