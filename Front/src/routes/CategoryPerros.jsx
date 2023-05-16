import { useEffect, useState, React } from 'react'
import Paginate from '../components/Paginate'
import { useContextGlobal } from '../components/utils/global.constext'

const CategoryPerros = () => {
    const {dataCategory, setDataCategory, items, setItems, currentPage, setCurrentPage,
        prevHandler, nextHandler, startHandler, endHandler} = useContextGlobal()

    return (
        <div className='space-section'>
            <h2>Finca</h2>
            <p>A continuacion nuestras opciones de guarderia en esta categoria</p>
            
            <Paginate currentPage={currentPage} items={items} 
            startHandler={startHandler} prevHandler={prevHandler} nextHandler={nextHandler} endHandler={endHandler}></Paginate>
        </div>
    )
}

export default CategoryPerros