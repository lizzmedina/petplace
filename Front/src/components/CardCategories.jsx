import { Link } from 'react-router-dom';
import { useContextGlobal } from '../components/utils/global.constext';
import { Categories } from '../routes/Categories';

export const Card = ({image, id, title,  description}) => {

const {url, setUrl, dataCategory, setDataCategory, items, setItems, currentPage, setCurrentPage,
        prevHandler, nextHandler, startHandler, endHandler} = useContextGlobal();

    return (
        <div className="card-container">
            <Link to={`http://localhost:8080/api/v1/petDayCare/category/${id}`}>
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