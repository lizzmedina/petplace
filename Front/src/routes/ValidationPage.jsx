
import 'sweetalert2/dist/sweetalert2.css';
import { useEffect  } from 'react';
import { useContextGlobal } from '../components/utils/global.constext';
//import { useParams } from 'react-router-dom';

export const ValidationPage = () => {

  const urlParams = new URLSearchParams(window.location.search);
  const encodedEmailUser = urlParams.get('email');
  const emailUser = decodeURIComponent(encodedEmailUser).replace('%20', '');
  const {validationUserUrl} = useContextGlobal();

  useEffect(() => {
    handlerValidation();
  }, [])
  
  const handlerValidation = () => {
    console.log('handler', emailUser);
    fetch(`${validationUserUrl}${emailUser.trim()}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      }
    })
  }

  return (
    <div className="validationpage-container space-section">
      <h2 className="h2-validationpage">¡Felicidades, su cuenta ha sido validada exitosamente!</h2>
      <h3 className="h2-validationpage" >Ahora puede ingresar con los datos registrados en "iniciar sesión" ↗️</h3>
    </div>
  );
};
