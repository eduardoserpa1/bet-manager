import React from 'react';
import ReactDOM from 'react-dom/client';
import { createGlobalStyle } from 'styled-components';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter, Routes, Route } from "react-router-dom"
import Welcome from './routes/Welcome';
import Home from './routes/Home';
import Bet from './routes/Bet';
import NewBet from './routes/NewBet';
import Apuration from './routes/Apuration';
import Result from './routes/Result';

const GlobalStyle = createGlobalStyle`
  html{
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',
      'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',
      sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  body{
    width: 100vw;
    margin: 0px;
    padding: 0px;
    overflow-x: hidden;
    background-color: #66ad8d;
  }

  code {
    font-family: source-code-pro, Menlo, Monaco, Consolas, 'Courier New',
      monospace;
  }
`



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <GlobalStyle />

    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Welcome />}></Route>
        <Route path='/home' element={<Home />}></Route>
        <Route path='/bet/:id' element={<Bet />}></Route>
        <Route path='/newbet/:id' element={<NewBet />}></Route>
        <Route path='/apuration/:id' element={<Apuration />}></Route>
        <Route path='/result/:id' element={<Result />}></Route>
      </Routes>
    </BrowserRouter>

  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
