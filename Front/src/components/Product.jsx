export const Product = ({ id, name, type, capacity, city, address, detail, image, basicPrice, }) => {

    return (

        <div key={id} className="product-container">
            <div className="path">Inicio/Alojamientos/{type}/{name}</div>
            <img src={image} alt={name} className="product-image" />
            <div className="product-info-container">
                <div className="product-info"></div>
                <h2>{name}</h2>
                <p className="text-info">Aloja {type}</p>
                <p className="text-info"> Capacidad: {capacity} {type}</p>
                <p className="city-info"> {city}</p>
                <span className="price-info">Precio: $ {basicPrice}</span>
                <p className="detail-info">{detail}</p>
                <button className="buttonDetail button-1">Reservar</button>
            </div>
        </div>
    )

}