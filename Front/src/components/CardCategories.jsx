
import { Link } from 'react-router-dom';

export const Card = ({image, title,  description}) => {


    return (
        <div className="card-container">
            <Link to={`/${title}`}>
                <img 
                    className="card-image"
                    src={image} 
                    alt={title} 
                />
                <h3 className="card-title-category">{title}</h3>
                <p className="card-quantity-categories">{description}</p>
            </Link>
        </div>
    )
}