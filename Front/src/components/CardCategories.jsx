import { Link } from 'react-router-dom';
import { useEffect, useState, React } from 'react'
import { useContextGlobal } from '../components/utils/global.constext'

export const Card = ({image, category, quantity}) => {
    const {dataCategory, setDataCategory, items, setItems, currentPage, setCurrentPage,
        prevHandler, nextHandler, startHandler, endHandler} = useContextGlobal()

    // ++++++++++++++++++++++++++++++++++++++
    //esta sera la logica para filtrar los productos bajo esta categoria y finalizar en un setDataCategory

    // ------Nota el feth se deberia hacer en context y luego aqui en componente 
    // filtrarlo para luego meter el array en el use effect

    // useEffect(() => {
    //     const datosFiltradosAPI = dataCategory.filter(producto => producto.type === 'Finca')
    //     setDataCategory(datosFiltradosAPI)
    // }, [])
    const datosFiltradosAPI = Array.from({length:40}, (value, index) => {
        return {id: index, name: `Item #${index}`}
    })
    
    useEffect(()=>{     
        setDataCategory(datosFiltradosAPI)
    }, [])

    return (
        <div className="card-container" onClick={startHandler}>
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
