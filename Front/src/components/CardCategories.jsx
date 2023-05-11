

export const Card = ({image, category, quantity}) => {
    return (
        <div className="card-container">
            <img 
                className="card-image"
                src={image} 
                alt={category} 
            />
            <h2 className="card-title-category">{category}</h2>
            <p className="card-quantity-categories">{quantity}</p>
        </div>
    )
}
