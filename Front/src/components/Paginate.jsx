import React from 'react'

const Paginate = (props) => {

    const items = props.items.map((item, index) => {
        return <li key={item.id}>esto es una card {item.title} </li>
    })

    return (
        <div>
            <h4>Items:</h4>

            <ul>
                {items}
            </ul>

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