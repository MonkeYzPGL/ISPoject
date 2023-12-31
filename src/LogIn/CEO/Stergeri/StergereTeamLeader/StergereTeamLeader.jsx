import React, { useState } from 'react';
import './StergereTeamLeader.css';

const StergereTeamLeader = ({ onBackClick }) => {
  const [TLID, setTLID] = useState('');
  const [isSubmissionSuccessful, setIsSubmissionSuccessful] = useState(false);

  const handleStergereTLClick = async () => {
    if (!TLID) {
      alert('Please fill in all fields before submitting.');
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/ceos/teamleader/${TLID}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (response.ok) {
        console.log('TeamLeader deleted successfully!');
        setIsSubmissionSuccessful(true);
      } else if (response.status === 404) {
        alert('TeamLeader not found.');
      } else {
        alert('Error deleting TeamLeader.');
      }
    } catch (error) {
      console.error('Error during TeamLeader deletion:', error);
      alert('Error deleting TeamLeader.');
    }
  };

  return (
    <div className="pageContainer">
      <h1 className="pageTitle">Sterge TeamLeaderii nedoriti din firma</h1>

      <label className="inputLabel">
        TeamLeader ID:
        <input
          type="text"
          className="textInput"
          value={TLID}
          onChange={(e) => setTLID(e.target.value)}
        />
      </label>

      <button className="atribuieButton" onClick={handleStergereTLClick}>
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

export default StergereTeamLeader;
