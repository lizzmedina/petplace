import React from 'react'

const PageModalWindow = ({children}) => {
  return (
    <article className='modal is-open'>
        <div className="modal-container"> 
          <div>
              <button className="modal-close">X</button>
          </div>
          <div>
             <h3 className='modal-compartir'>Compartir</h3>
             <div className='modal-compartir2'>{children}</div>
          </div>
        </div>
    </article>
  )
}

export default PageModalWindow