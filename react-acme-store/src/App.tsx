import React from 'react';
import './App.css';

import StoreListComponent from "./store-list/store-list-container";

const App: React.FC = () => {

  return (
    <div className="App">
      <header className="App-header">
        <StoreListComponent></StoreListComponent>
      </header>
    </div>
  );
}

export default App;
