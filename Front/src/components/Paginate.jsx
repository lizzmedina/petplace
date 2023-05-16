import React from 'react'
import { CardRecomends } from "./CardRecomends";

const Paginate = (props) => {

    const items = props.items.map((recommend) => {
        return <CardRecomends
            key={recommend.id}
            type={recommend.type}
            name={recommend.name}
            image={recommend.image}
            quantity={recommend.quantity}
            rating={recommend.rating}
            ratingText={recommend.ratingText}
            iconoLocation={recommend.iconoLocation}
            city={recommend.city}
            address={recommend.address}
            service1={recommend.service1}
            service2={recommend.service2}
            detail={recommend.detail}
        />
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