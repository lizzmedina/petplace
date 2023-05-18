import React from 'react';

export const BackLink = ({ history }) => {
  const goBack = () => {
    history.goBack();
  };

  return (
    <a href='/' onClick={goBack} className="back-link"><img className='back-icon' src="..\images\backRecurso 2.png" alt="" /></a>
  );
};


