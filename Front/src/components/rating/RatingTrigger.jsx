import React from 'react';
const RatingTrigger = ({ triggerText, buttonRef, showModal, userId, bookingId }) => {
  return (
    <button
      className="btn button-2 btn-calificar center modal-button"
      ref={buttonRef}
      onClick={showModal}
    >
      {triggerText}
    </button>
  );
};
export default RatingTrigger;
