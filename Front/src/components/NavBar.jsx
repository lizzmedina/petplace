import { Link } from 'react-router-dom';
import '.././styles.css';

export const NavBar = () => {
  return (
    <nav className = 'navbar'>
    
      <Link className = 'navbar-link-container' to = "/"> 
        <img 
          src='./images/pet-houseblanco.png'  
          alt="icono"
          className="logo"
        /> 
        <p className='slogan-navbar'> Los alojamos como en casa </p>
      </Link> 
    
      <div className ='navbar-buttons'>
        <button className ='add-acount-button'><Link to='/register'>Crear Cuenta</Link></button>
        <button className ='start-session-button'>Iniciar Sesión</button>
      </div>
    
    </nav>
    
  )
}
