import React from 'react'
import { useEffect, useState } from "react";
import { Product } from "../components/Product.jsx";
import { useParams } from 'react-router-dom'

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



  return (
    <div>CategoryFiltred</div>
  )
}

export default CategoryFiltred