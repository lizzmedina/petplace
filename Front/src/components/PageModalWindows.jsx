import React from 'react'
import PageModalWindow from './PageModalWindow'
import { useModal } from '../hooks/useModal'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFacebook, faTwitter, faInstagramSquare, faFacebookMessenger} from '@fortawesome/free-brands-svg-icons'
import { faStar, faMessage, faEnvelope, faSheetPlastic } from '@fortawesome/free-solid-svg-icons'

const PageModalWindows = () => {
    const [isOpenModal1, openModal1, closeModal1] = useModal(false);  
  return (
    <div>
        <button onClick={openModal1}>Compartir</button>
        <PageModalWindow isOpen={isOpenModal1} closeModal={closeModal1}>
         <div className='iconsModal2'>
               <div >
                  <div className='iconsModalsSpecific'>
                     <FontAwesomeIcon icon={ faMessage} size="xl" style={{color: "#000000",}} />
                     <p className='ParrafoModal1'>Whatsapp</p>
                  </div>
                  <div className='iconsModalsSpecific'>
                   <FontAwesomeIcon icon={faFacebook} size='xl' style={{color: "#000000",}} />
                   <p className='ParrafoModal1'>Facebook</p>
                  </div>
               </div>

                <div>
                  <div className='iconsModalsSpecific'>
                        <FontAwesomeIcon icon={faInstagramSquare} size="xl" style={{color: "#000000",}} />
                         <p className='ParrafoModal1'>Instagram</p>
                  </div>
                   <div className='iconsModalsSpecific'>
                      <FontAwesomeIcon icon={faFacebookMessenger} style={{color: "#000000",}} size="xl"/>
                       <p className='ParrafoModal1'>Messenger</p>
                   </div>
              </div>

               <div>
                  <div className='iconsModalsSpecific'>
                     <FontAwesomeIcon icon={faEnvelope} size="xl" style={{color: "#000000",}} />
                     <p className='ParrafoModal1'>Email</p>
                  </div>
                  <div className='iconsModalsSpecific'>
                      <FontAwesomeIcon icon={faSheetPlastic} size="xl" style={{color: "#000000",}} />
                      <p className='ParrafoModal1'>Enlace</p>
                  </div>
             </div>
         </div>   
             
              
            
            
        </PageModalWindow>
        
    </div>
  )
}

export default PageModalWindows