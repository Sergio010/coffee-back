//App.jsx
import React from 'react'
import { HashRouter, Route, Routes } from 'react-router-dom';
import { Navbar } from './components/General/Navbar/Navbar.jsx';

function App() {
  return (
    <HashRouter>
      <Menu />
      <Routes>
        <Route path="/" element={<Home/>}/> 
        <Route path="/Navbar" element={<Navbar />}/> 
        <Route path="/Hero" element={<Hero />}/> 
        <Route path="/Services" element={<Services />}>
        <Route path="/Bottom" element={<Bottom />}>
          <Route path=":url" element={<MiCurso />}/> 
        </Route> 
        <Route path="*" element={<p>Ups, no existe la ruta</p>}/> 
      </Routes>
    </HashRouter>

  );
}

export default App;