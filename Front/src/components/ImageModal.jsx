import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCircleXmark} from '@fortawesome/free-regular-svg-icons'
import { Carousel } from 'react-responsive-carousel';


const ImageModal = ({ images, onClose }) => {
  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <FontAwesomeIcon className='closeModalGallery' icon={faCircleXmark} onClick={onClose} />
        <Carousel axis='horizontal' centerMode="true" width="80%" dynamicHeight="true" infiniteLoop="true" >
          {images.map((img, index) => (
            <div key={index}>
              <img className='carousel-images' src={img} alt={`Imagen ${index + 1}`} />
            </div>
          ))}
        </Carousel>
      </div>
    </div>
  );
};

export default ImageModal;