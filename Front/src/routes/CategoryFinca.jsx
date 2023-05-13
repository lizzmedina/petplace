import { useState, React } from 'react'
import Paginate from '../components/Paginate'
import { useContextGlobal } from '../components/utils/global.constext'

const CategoryFinca = () => {
    const {prevHandler, nextHandler} = useContextGlobal()

    const datosAPI = Array.from({length:60}, (value, index) => {
        return {id: index, title: `Item #${index}`}
    })

    const[dataFromAPI, setDataFromAPI] = useState(datosAPI)

    const itemsPerPage = 10
    const[items, setItems] = useState([...dataFromAPI].splice(0, itemsPerPage))

    return (
        <div className='space-section'>
            <h2>Finca</h2>
            <p>A continuacion nuestras opciones de guarderia en esta categoria</p>
            
            <Paginate currentPage={0} items={items} prevHandler={prevHandler} nextHandler={nextHandler}></Paginate>
        </div>
    )
}

export default CategoryFinca