import { useNavigate } from 'react-router-dom'


export const Product = ({ id, name, type, capacity, city, address, detail, image, basicPrice, }) => {

    const navigate = useNavigate()

    if (!image) {
        return image;
    }
    return (

        <div key={id} className="product-container">
            <div className="product-header">
                <span className="product-location">{city}, {address}</span>  <a onClick={() => navigate(-1)}><img className='back-icon' src="..\images\backRecurso 2.png" alt="" /></a>
            </div>
            <div className="product-galery">
                <div className='leading-image'>
                    <img src={image[0]} alt="Imagen principal" className="product-image" />
                </div>
                <div className='underling-images'>
                    <div className='two-image'>
                        <img src={image[1]} alt="imagen scundaria 1" className="product-image" />
                    </div>
                    <div className='three-image'>
                        <img src={image[2]} alt="imagen scundaria 1" className="product-image" />
                    </div>
                    <div className='four-image'>
                        <img src={image[2]} alt="imagen scundaria 1" className="product-image" />
                    </div>
                    <div className='five-image'>
                        <img src={image[1]} alt="imagen scundaria 1" className="product-image" />
                    </div>
                </div>
                
            </div>
            <p className='more-images'> Ver más</p>


            <div className="product-info-container">

                <span className="product-info-left">
                    <h2>{name}</h2>
                    <p className="text-info">Aloja {type}</p>
                    <p className="detail-info">{detail}</p>
                </span>

                <span className="product-info-rigth">
                    <p className="text-info"> Capacidad: {capacity} {type}</p>
                    <span className="price-info">Precio: $ {basicPrice}</span>
                    <button className="buttonDetail button-1">Reservar</button>
                </span>


            </div>

        </div>
    )

}