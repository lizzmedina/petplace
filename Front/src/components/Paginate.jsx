import React from 'react'
import { CardRecomends } from "./CardRecomends";
import { Link } from 'react-router-dom'

const Paginate = (props) => {

    const items = props.items.map((recommend) => {
        return <Link key={recommend.id} to={'/Detail/' + recommend.id}><CardRecomends
            key={recommend.id}
            type={recommend.type}
            name={recommend.name}
            image={recommend.image}
            capacity={recommend.capacity}
            rating={recommend.rating}
            ratingText={recommend.ratingText}
            iconoLocation={recommend.iconoLocation}
            city={recommend.city}
            address={recommend.address}
            service1={recommend.service1}
            service2={recommend.service2}
            detail={recommend.detail}
            basicPrice={recommend.basicPrice}
        /></Link>
    })

    return (
        <div>
            <div className='paginate-container'>
                {items}
            </div>

            <div className='paginate-button-container'>
                <button onClick={props.startHandler}> Inic </button>
                <button onClick={props.prevHandler}> Prev </button>
                <h6> Pagina: {props.currentPage} </h6>
                <button onClick={props.nextHandler}> Sig </button>
                <button onClick={props.endHandler}> Fin </button>
            </div>
        </div>
    )
}

export default Paginate