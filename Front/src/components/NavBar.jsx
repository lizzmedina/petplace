import { Link } from 'react-router-dom';
import '.././styles.css';
import '.././header.css';

export const NavBar = () => {
  return (
    <div className = 'navbar'>
    
      <Link className = 'navbar-link-container' to = "/"> 
        <img 
          src='./images/PpLogo.png'  
          alt="icono"
          className="logo"
        /> 
        <p className='slogan-navbar'>   Los cuidamos como en casa </p>
      </Link> 

      <div class="burgermenu">
        <input type="checkbox"/>
        <i className = "fas fa-bars" id="burger-menu"></i>
        <i className = "fas fa-times"></i>
        <nav class="navbar">
          <ul id="menu">
            <li className = "item"> < a className = "change-button "  href="/productList"><Link to='/register'>Crear Cuenta</Link></a></li>
            <li className = "item"> < a className = "change-button"  href="/productList"><Link to='/login'>Iniciar Sesión</Link></a></li>
          </ul>
        </nav>
      </div>
    </div>
    
  )
}
