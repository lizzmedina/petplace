import { Link, useNavigate } from 'react-router-dom';
import '.././styles.css';
import '.././header.css';
import * as React from 'react';
import Box from '@mui/material/Box';
import Avatar from '@mui/material/Avatar';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import IconButton from '@mui/material/IconButton';
import Tooltip from '@mui/material/Tooltip';
import Divider from '@mui/material/Divider';
import { createTheme, ThemeProvider } from '@mui/material/styles';

export const NavBar = () => {

  const navigate = useNavigate();
  const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null;

  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
  const handleCloseSesion = () => {
    localStorage.removeItem("userConnected");
    navigate('/', { replace: true });
    window.location.reload();
  };
  

  const theme = createTheme({
    typography: {
      fontFamily: 'Quicksand, sans-serif, ',
    },
  });


  return (
    <div className = 'navbar'>
    
      <Link className = 'navbar-link-container' to = "/"> 
        
        <img 
          src='https://bucket-equipo2-frontend-imagenes.s3.us-east-2.amazonaws.com/Logo/LogoDB.png'  
          alt="icono"
          className="logo"
        /> 
        <p className='slogan-navbar'>  Los cuidamos como en casa </p>
      </Link>

      {/*a continuacion el menu desplegable de la libreria Mui */}
      {userConnected && (
        <ThemeProvider theme={theme}>
          <Box sx={{ display: 'flex', alignItems: 'center', textAlign: 'center' }}>
            <Tooltip title="Account settings">
              <IconButton
                onClick={handleClick}
                size="small"
                sx={{ mx: 3 }}
                aria-controls={open ? 'account-menu' : undefined}
                aria-haspopup="true"
                aria-expanded={open ? 'true' : undefined}
              >
                <Avatar sx={{ width: 40, height: 40, backgroundColor: '#8ED1B9' }}>{userConnected.name[0].toUpperCase()}{userConnected.lastName[0].toUpperCase()}</Avatar>        
              </IconButton>
            </Tooltip>
          </Box>
        </ThemeProvider>
      )}
      {userConnected && (
        <ThemeProvider theme={theme}>
          <Menu
          anchorEl={anchorEl}
          id="account-menu"
          open={open}
          onClose={handleClose}
          onClick={handleClose}
          PaperProps={{
            elevation: 0,
            sx: {
              overflow: 'visible',
              filter: 'drop-shadow(0px 2px 8px rgba(0,0,0,0.32))',
              mt: 1.5,
              '& .MuiAvatar-root': {
                width: 32,
                height: 32,
                ml: -0.5,
                mr: 1,
              },
              '&:before': {
                content: '""',
                display: 'block',
                position: 'absolute',
                top: 0,
                right: 14,
                width: 10,
                height: 10,
                bgcolor: 'background.paper',
                transform: 'translateY(-50%) rotate(45deg)',
                zIndex: 0,
              },
            },
          }}
          transformOrigin={{ horizontal: 'right', vertical: 'top' }}
          anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
          >
            <MenuItem><Link to={"/account/" + userConnected.id} style={{ color: 'inherit' }}> Mi cuenta </Link></MenuItem>
            <Divider />
            <MenuItem onClick={handleCloseSesion}> Cerrar Sesión </MenuItem>
          </Menu>
        </ThemeProvider>
      )}


      {!userConnected && (
        <div className="burgermenu">
        <input type="checkbox"/>
        <i className = "fas fa-bars" id="burger-menu"></i>
        <i className = "fas fa-times"></i>
        <nav className="navbar">
          <ul id="menu">
            <li className = "item"> < a className = "change-button "  href="/productList"><Link to='/register'>Crear Cuenta</Link></a></li>
            <li className = "item"> < a className = "change-button"  href="/productList"><Link to='/login'>Iniciar Sesión</Link></a></li>
          </ul>
        </nav>
      </div>
      )}

    </div>
  )
}
