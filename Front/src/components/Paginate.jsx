import React from 'react'
import { CardRecomends } from "./CardRecomends";

const Paginate = (props) => {

    const items = props.items.map((item) => {
        return <CardRecomends
            key={item.id}
            category={item.category}
            name={item.name}
            image={item.image}
            quantity={item.quantity}
            rating={item.rating}
            ratingText={item.ratingText}
            iconoLocation={item.iconoLocation}
            location={item.location}
            service1={item.service1}
            service2={item.service2}
            description={item.description}
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