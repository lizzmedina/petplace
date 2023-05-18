import { BackLink } from "./BackLink.jsx";


export const Product = ({ id, name, type, capacity, city, address, detail, image, basicPrice, }) => {
    
    

    return (

        <div key={id} className="product-container">
            <div className="product-header">
                <span className="product-location">{city}, {address}</span> <span className="product-back"><BackLink/></span>
            </div>
            <div className="product-section-image"> <img src={image} alt={name} className="product-image" /></div>


            
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