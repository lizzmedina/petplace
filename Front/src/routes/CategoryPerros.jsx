import { useEffect, useState, React } from 'react'
import Paginate from '../components/Paginate'
import { useContextGlobal } from '../components/utils/global.constext'


const CategoryPerros = () => {
    const {url, setUrl, dataCategory, setDataCategory, items, setItems, currentPage, setCurrentPage,
        prevHandler, nextHandler, startHandler, endHandler} = useContextGlobal()

        useEffect(() => {
            setUrl("http://localhost:8080/api/v1/petDayCare/category/Perros")
        },[setUrl])

    return (
        <div className='space-section'>
            <h2>Perros</h2>
            <p>A continuacion nuestras opciones de hospedaje en esta categoria</p>
            
            <Paginate currentPage={currentPage} items={items} 
            startHandler={startHandler} prevHandler={prevHandler} nextHandler={nextHandler} endHandler={endHandler}></Paginate>
        </div>
    )
}

export default CategoryPerros