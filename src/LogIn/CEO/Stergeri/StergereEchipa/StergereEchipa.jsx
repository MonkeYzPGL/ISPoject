import React, { useState } from 'react';
import './StergereEchipa.css';

const StergereEchipa = ({ onBackClick }) => {
  const [echipaID, setEchipaID] = useState('');
  const [isSubmissionSuccessful, setIsSubmissionSuccessful] = useState(false);

  const handleStergereEchipaClick = async () => {
    if (!echipaID) {
      alert('Please fill in all fields before submitting.');
      return;
    }

  };

  return (
    <div className="pageContainer">
      <h1 className="pageTitle">Sterge o echipa din firma</h1>

      <label className="inputLabel">
        Echipa ID:
        <input
          type="text"
          className="textInput"
          value={echipaID}
          onChange={(e) => setEchipaID(e.target.value)}
        />
      </label>

      <button className="atribuieButton" onClick={handleStergereEchipaClick}>
        Stergere
      </button>

      {isSubmissionSuccessful && (
        <p style={{ color: 'green', marginTop: '8px', fontSize: '20px' }}>
          Stergerea a fost realizata cu succes.
        </p>
      )}

      <button onClick={onBackClick}>Back</button>
    </div>
  );
};

export default StergereEchipa;
