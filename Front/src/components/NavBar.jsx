import { Link } from 'react-router-dom';
import '.././styles.css';

export const NavBar = () => {
  return (
    <nav className = 'navbar'>
    
      <Link className = 'navbar-link-container' to = "/"> 
        <img 
          src='./images/PpLogo.png'  
          alt="icono"
          className="logo"
        /> 
        <p className='slogan-navbar'>   Los cuidamos como en casa </p>
      </Link> 
    
      <div className ='navbar-buttons'>
        <button className ='button-1'><Link to='/register'>Crear Cuenta</Link></button>
        <button className ='button-1'><Link to='/login'>Iniciar Sesión</Link></button>
      </div>
    
    </nav>
    
  )
}
