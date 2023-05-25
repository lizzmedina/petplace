import { useNavigate} from 'react-router-dom'


export const Product = ({ id, name, type, capacity, city, address, detail, image, basicPrice, }) => {
    
    const navigate = useNavigate()

    return (

        <div key={id} className="product-container">
            <div className="product-header">
                <span className="product-location">{city}, {address}</span>  <a onClick={() => navigate(-1)}><img className='back-icon' src="..\images\backRecurso 2.png" alt="" /></a>
            </div>
            <div className="product-section-image"> <img src={image[0]} alt={name} className="product-image" /></div>


            
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