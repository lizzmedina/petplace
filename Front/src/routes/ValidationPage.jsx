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
    if (user.email) {
      fetch(`http://localhost:8080/api/v1/user/validation/${user.email}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        }
      })
      .then(() => {
        Swal.fire({
          html: 'Validado con éxito, ahora puedes <a href="http://localhost:5173/login">ingresar a tu cuenta</a>',
          icon: "success",
          showConfirmButton: false,
        });
      })
      .catch((error) => {
        console.error("Error al validar:", error);
      });
    }
  };

  return (
    <div>
      <h2>Pagina de validación de correo electrónico</h2>

      <input
        type="email"
        className="type-1"
        value={user.email}
        onChange={(e) => setUser({ email: e.target.value })}
      />

      <button className="button-1" onClick={handlerValidation}>
        Validar
      </button>
    </div>
  );
};
