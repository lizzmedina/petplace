import { useEffect, useState } from "react";
import { Product } from "../components/Product.jsx";
import { useParams } from 'react-router-dom'
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { CalendarDetail } from "../components/CalendarDetail.jsx";

const Details = () => {

      const [details, setDetails] = useState([])
      const { id } = useParams()

      const getDetail = async()=>{
            const res = await fetch(`${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/petDayCare/detail/${id}`)
            const data = await res.json()
            setDetails(data)
      }
      useEffect(()=>{
            getDetail()
      }, [])

      return (
            <>
            <div className="detail-container">  
                  <Product
                        id={details.id}
                        type={details.type}
                        name={details.name}
                        image={details.images}
                        capacity={details.capacity}
                        address={details.address}
                        city={details.city}
                        detail={details.detail}
                        basicPrice={details.basicPrice}
                        characteristics={details.characteristics}
                  />
            </div>
            <div>
                  <CalendarDetail/>
            </div>
            
            </>
            
      )
}

export default Details;