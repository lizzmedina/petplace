import { Link } from 'react-router-dom';

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
        <button className ='buttons-navbar'><Link to='/register'>Crear Cuenta</Link></button>
        <button className ='buttons-navbar'><Link to='/login'>Iniciar Sesión</Link></button>
      </div>
    
    </nav>
    
  )
}
