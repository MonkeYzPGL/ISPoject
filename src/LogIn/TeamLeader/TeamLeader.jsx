import React from 'react';
import './TeamLeader.css';

const FormHeader = (props) => <h2 id="headerTitle">{props.title}</h2>;

const FormTitle = (props) => <p id="formTitle">{props.title}</p>;


const TeamLeader = ({ onLogoutClick }) => {
  return (
    <div className="teamLeader-page">
      <div className="container">
        <FormHeader title="Bun venit, liderule!" />
        <FormTitle title="Gestioneaza echipa si proiectul" />
        <div className="button-container-sefSectie">
          <button>Gestiune Echipa</button>
          <button>Gestiune Proiect</button>
          <button onClick={onLogoutClick}>Logout</button>
        </div>
      </div>
    </div>
  );
};

export default TeamLeader;
