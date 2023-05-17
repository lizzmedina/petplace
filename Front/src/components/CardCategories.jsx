import { Link } from 'react-router-dom';
import { useEffect, useState, React } from 'react'
import { useContextGlobal } from '../components/utils/global.constext'

export const Card = ({image, category, quantity}) => {
    const {url, setUrl, dataCategory, setDataCategory, items, setItems, currentPage, setCurrentPage,
        prevHandler, nextHandler, startHandler, endHandler} = useContextGlobal()

    return (
        <div className="card-container">
            <Link to={`/${category}`}>
                <img 
                    className="card-image"
                    src={image} 
                    alt={category} 
                />
                <h3 className="card-title-category">{category}</h3>
                <p className="card-quantity-categories">{quantity}</p>
            </Link>
        </div>
    )
}
