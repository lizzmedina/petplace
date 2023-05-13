import { useState, React } from 'react'
import Paginate from '../components/Paginate'
import { useContextGlobal } from '../components/utils/global.constext'

const CategoryFinca = () => {
    const {dataCategory, setDataCategory, items, setItems, currentPage, setCurrentPage,
        prevHandler, nextHandler, startHandler, endHandler} = useContextGlobal()

    //esta sera la logica para filtrar los productos bajo esta categoria y finalizar en un setDataCategory
    const dataCategory2 = "datosAPI"

    

    return (
        <div className='space-section'>
            <h2>Finca</h2>
            <p>A continuacion nuestras opciones de guarderia en esta categoria</p>
            
            <Paginate currentPage={currentPage} items={items} 
            startHandler={startHandler} prevHandler={prevHandler} nextHandler={nextHandler} endHandler={endHandler}></Paginate>
        </div>
    )
}

export default CategoryFinca