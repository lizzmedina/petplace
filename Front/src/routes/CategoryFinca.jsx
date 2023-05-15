import { useEffect, useState, React } from 'react'
import Paginate from '../components/Paginate'
import { useContextGlobal } from '../components/utils/global.constext'

const CategoryFinca = () => {
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

    // ++++++++++++++++++++++++++++++++++++++++++


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