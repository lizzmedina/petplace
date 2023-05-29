
export const Mail = () => {
    return (
        <div className="mail-container">
            <span> <img src="./images/PpLogo.png" alt="logo petplace.com" /></span>
            <h3> Correo de confirmación </h3>
            <h5> Sí estás haciendo el proceso de creación de cuenta en PetPlace.com, por favor verifica tu cuenta, en menos de 48 horas,  haciendo clic Aquí: </h5>
            <Link>
                <button>Verificar</button>
            </Link>
            <p>si no reconoces esta actividad, por favor omitir este correo. </p>
            <h6>A continuación te llegará otro mail de validación, para confirmarte que se ha creado con exito tú cuenta, refresca y revisa tu correo electronico en unos minutos. Gracias por tu paciencia. </h6>
            <p>Recuerda que pasadas las 48 horas este correo ya no será válido para tu confirmación, por lo que deberás ingresar nuevamente a nuestra página y hacer el proceso de creación de cuenta nuevamente. </p>
            <Link>
                <p>PetPlace.com</p>
            </Link>
        </div>
    )
}

