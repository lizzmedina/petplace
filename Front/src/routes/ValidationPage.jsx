import React, { useState } from "react";
import { useContextGlobal } from "../components/utils/global.constext";
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.css';

export const ValidationPage = () => {

  const { validationUserUrl } = useContextGlobal();
  const [user, setUser] = useState({
    email: ""
  });

  const handlerValidation = () => {
    fetch(`${validationUserUrl}${user.email}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      }
    })
    .then((response) => {
      if (response.ok) {
        Swal.fire({
          html: 'Validado con éxito, ahora puedes <a href="/login">ingresar a tu cuenta</a>',
          icon: "success",
          showConfirmButton: false,
        });
      } else {
        Swal.fire({
          text: 'error al validar, revisa que esté bien escrito tú correo e intenta nuevamente, por favor',
          icon: "error",
        });
      }
    })
    .catch((error) => {
      console.error("Error al validar:", error);
    });
  }

  return (
    <div className="validationpage-container space-section">
      <h2 className="h2-validationpage">Por favor ingresa el correo registrado para validarlo:</h2>
      
      <input
        type="email"
        className="input-validation"
        value={user.email}
        onChange={(e) => setUser({ email: e.target.value })}
      />
      <button className="button-1" onClick={handlerValidation}>
        Validar
      </button>
    </div>
  );
};
