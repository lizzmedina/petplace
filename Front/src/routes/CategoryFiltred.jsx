import React from 'react'
import { useEffect, useState } from "react";
import { useParams } from 'react-router-dom'
import Paginate from '../components/Paginate'
import { useContextGlobal } from '../components/utils/global.constext'

function CategoryFiltred() {

  const [category, setCategory] = useState([])

  const { id } = useParams()

  const getCategory = async()=>{
        const res = await fetch(`http://localhost:8080/api/v1/petDayCare/category/${id}`)
        const data = await res.json()
        setCategory(data)
  }

  useEffect(()=>{
    getCategory()
  }, [])

  const {places, setPlaces, url, setUrl, dataCategory, setDataCategory, items, setItems, currentPage, setCurrentPage,
    prevHandler, nextHandler, startHandler, endHandler} = useContextGlobal()

    useEffect(() => {
      setUrl(`http://localhost:8080/api/v1/petDayCare/category/${id}`)
  },[])

  return (
    <div className='space-section'>
    <h2>{places[id-1].title}</h2>
    <p>A continuacion nuestras opciones de hospedaje en esta categoria</p>
    
    <Paginate currentPage={currentPage} items={items} 
    startHandler={startHandler} prevHandler={prevHandler} nextHandler={nextHandler} endHandler={endHandler}></Paginate>
</div>
  )
}

export default CategoryFiltred