
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFacebook, faTwitter, faDiscord } from '@fortawesome/free-brands-svg-icons'
import { faCopyright } from '@fortawesome/free-regular-svg-icons'



export const Footer = () => {
  return (
    <footer>
      <div className="copiryghtIcon">
        <FontAwesomeIcon className='socialIcon'  icon={faCopyright} />
        <p> 2023 Digital Booking </p>
      </div>
      <div className="socialMedia">
        <FontAwesomeIcon className='socialIcon' icon={faFacebook} size="xl" />
        <FontAwesomeIcon className='socialIcon' icon={faTwitter} size="xl" />
        <FontAwesomeIcon className='socialIcon' icon={faDiscord} size="xl" />
      </div>
    </footer>
  )
}
