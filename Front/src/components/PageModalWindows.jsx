import {useState} from "react";
import PageModalWindow from "./PageModalWindow";
import { useModal } from "../hooks/useModal";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faFacebook,
  faTwitter,
  faInstagramSquare,
  faFacebookMessenger,
} from "@fortawesome/free-brands-svg-icons";
import {
  faStar,
  faMessage,
  faEnvelope,
  faSheetPlastic,
} from "@fortawesome/free-solid-svg-icons";
import {CopyToClipboard} from "react-copy-to-clipboard";

const PageModalWindows = ({ name, basicPrice, city, address, image }) => {
  const [isOpenModal1, openModal1, closeModal1] = useModal(false);

  const [copied, setCopied] = useState(false)


  return (
    <div>
      <button onClick={openModal1}>Compartir</button>

      <PageModalWindow
        isOpen={isOpenModal1}
        closeModal={closeModal1}
        name={name}
        basicPrice={basicPrice}
        city={city}
        address={address}
        image={image}
      >
        <div className="iconsModal2">
          <div className="iconsSpecificColumns">
            <div>
              <a href="https://wa.me/573058117864?text=Mira%20esta%20propiedad%20que%20ví%20en%20PetPlace" target="_blank">
                <div className="iconsModalsSpecific iconsModalsSpecific-A">
                  <FontAwesomeIcon
                    icon={faMessage}
                    size="xl"
                    style={{ color: "#000000" }}
                  />
                  <p className="ParrafoModal1">Whatsapp</p>
                </div>
              </a>
            </div>
            <a href="https://www.facebook.com/PetPlaceDB" target="_blank">
              <div className="iconsModalsSpecific iconsModalsSpecific-B">
                <FontAwesomeIcon
                  icon={faFacebook}
                  size="xl"
                  style={{ color: "#000000" }}
                />
                <p className="ParrafoModal1">Facebook</p>
              </div>
            </a>

            <a href="https://www.instagram.com/" target="_blank">
              <div className="iconsModalsSpecific iconsModalsSpecific-C">
                <FontAwesomeIcon
                  icon={faInstagramSquare}
                  size="xl"
                  style={{ color: "#000000" }}
                />
                <p className="ParrafoModal1">Instagram</p>
              </div>
            </a>
          </div>

          <div className="iconsSpecificColumns2">
            <a href="https://www.messenger.com/" target="_blank">
              <div className="iconsModalsSpecific iconsModalsSpecific-D">
                <FontAwesomeIcon
                  icon={faFacebookMessenger}
                  style={{ color: "#000000" }}
                  size="xl"
                />
                <p className="ParrafoModal1">Messenger</p>
              </div>
            </a>

            <a href="https://www.gmail.com." target="_blank">
              <div className="iconsModalsSpecific iconsModalsSpecific-E">
                <FontAwesomeIcon
                  icon={faEnvelope}
                  size="xl"
                  style={{ color: "#000000" }}
                />
                <p className="ParrafoModal1">Email</p>
              </div>
            </a>

            <CopyToClipboard text={window.location.href}>
             <div onClick={()=>setCopied(true)} className="iconsModalsSpecific iconsModalsSpecific-F">
                <FontAwesomeIcon
                  icon={faSheetPlastic}
                  size="xl"
                  style={{ color: "#000000" }}
                />
                {<p className={`ParrafoModal1 ${copied && `ParrafoModal1-copy`}`}>Enlace</p>}  
              </div>
            </CopyToClipboard>
              

          </div>
        </div>
      </PageModalWindow>
    </div>
  );
};

export default PageModalWindows;
