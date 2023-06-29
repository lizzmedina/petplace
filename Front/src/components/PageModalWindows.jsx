import { useState } from "react";
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
  faShareNodes
} from "@fortawesome/free-solid-svg-icons";
// import { faShareNodes } from "@fortawesome/free-regular-svg-icons";
import { CopyToClipboard } from "react-copy-to-clipboard";
import { Link } from "react-router-dom";

const PageModalWindows = ({ name, basicPrice, city, address, image, type }) => {
  const [isOpenModal1, openModal1, closeModal1] = useModal(false);

  const [copied, setCopied] = useState(false)


  return (
    <div>

      <Link onClick={openModal1}>
        <FontAwesomeIcon
          icon={faShareNodes}
          size="xl"
          style={{ color: "#a278f0" }}
        />
      </Link>

      <PageModalWindow
        isOpen={isOpenModal1}
        closeModal={closeModal1}
        name={name}
        basicPrice={basicPrice}
        city={city}
        address={address}
        image={image}
      >

        <div className="iconsSpecificColumns">

          <a href="https://wa.me/573058117864?text=Mira%20esta%20propiedad%20que%20ví%20en%20PetPlace" target="_blank">
            <div className="iconsModalsSpecific">
              <FontAwesomeIcon
                icon={faMessage}
                size="xl"

              />
              <p className="ParrafoModal1">Whatsapp</p>
            </div>
          </a>

          <a href="https://www.facebook.com/PetPlaceDB" target="_blank">
            <div className="iconsModalsSpecific">
              <FontAwesomeIcon
                icon={faFacebook}
                size="xl"

              />
              <p className="ParrafoModal1">Facebook</p>
            </div>
          </a>

          <a href="https://www.instagram.com/petplacecomoencasa/" target="_blank">
            <div className="iconsModalsSpecific">
              <FontAwesomeIcon
                icon={faInstagramSquare}
                size="xl"

              />
              <p className="ParrafoModal1">Instagram</p>
            </div>
          </a>

          <a href="https://www.messenger.com/" target="_blank">
            <div className="iconsModalsSpecific">
              <FontAwesomeIcon
                icon={faFacebookMessenger}

                size="xl"
              />
              <p className="ParrafoModal1">Messenger</p>
            </div>
          </a>

          <a href="mailto:petplacecomoencasa@gmail.com" target="_blank">
            <div className="iconsModalsSpecific">
              <FontAwesomeIcon
                icon={faEnvelope}
                size="xl"

              />
              <p className="ParrafoModal1">Email</p>
            </div>
          </a>

          <CopyToClipboard text={window.location.href}>
            <div onClick={() => setCopied(true)} className="iconsModalsSpecific">
              <FontAwesomeIcon
                icon={faSheetPlastic}
                size="xl"

              />
              {<p className={`ParrafoModal1 ${copied && `ParrafoModal1-copy`}`}>Enlace <p className="pasted"></p></p>  }
            </div>
          </CopyToClipboard>


        </div>

      </PageModalWindow>
    </div>
  );
};

export default PageModalWindows;
