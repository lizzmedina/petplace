import React from 'react'

const Paginate = (props) => {

    const items = props.items.map((item, index) => {
        return <li key={item.id}>esto es una card {item.title} </li>
    })

    return (
        <div>
            <h3>Pagina: {props.currentPage}</h3>

            <button onClick={props.prevHandler}> Prev </button>
            <button onClick={props.nextHandler}> Next </button>

            <h2>Items:</h2>

            <ul>
                {items}
            </ul>

        </div>
    )
}

export default Paginate