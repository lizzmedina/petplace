import React from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { } from '@fortawesome/free-brands-svg-icons'
import { faCity, faDollarSign, faRoad} from '@fortawesome/free-solid-svg-icons'

const PageModalWindow = ({
  children, 
  isOpen,
  closeModal, 
  name,
  basicPrice,
  city,
  address,
  image,
}) => {

  const handleModalContainerClick = e => e.stopPropagation();

  return (
    <article className={`modal ${isOpen && "is-open"}`} onClick={closeModal}>
      <div className="modal-container" onClick={handleModalContainerClick}>
        <div>
          <button onClick={closeModal} className="modal-close">X</button>
        </div>
        <div>
          <h3 className="modal-compartir">Compartir </h3>

          <div className="contectCardModal">
            {/* <div>
              <h5>Detalles:</h5>
            </div> */}
            <div className="contectCardModal-img" >
              <img src={image} width={120} height={120} alt="" />
            </div>
            <div className="contectCardModal-describe">
              <blockquote>
              <h3>{name}</h3>
              <div className="contectCardModalInsade">
                <p> <FontAwesomeIcon icon={faCity} size="sm" style={{color: "#a278f0",}} /> {city}</p>
                <p> <FontAwesomeIcon icon={faRoad} size="sm" style={{color: "#a278f0",}} /> {address}</p>
                <p><FontAwesomeIcon icon={faDollarSign} size="sm" style={{color: "#a278f0",}} /> {basicPrice}</p>
              </div>
              </blockquote>
            </div>
          </div>

          <div className="ejemplo">
            <div className="modal-compartir2">{children}</div>
          </div>
        </div>
      </div>
    </article>
  );
};

export default PageModalWindow;
