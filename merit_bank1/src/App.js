import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import './bootstrap.css';
import LoginComponent from './components/mb-component/MeritBankApp';
import HeaderComponent from './components/common/HeaderComponent';
import FooterComponent from './components/common/FooterComponent';
import Counter from './components/counter/Counter';
import MeritBankApp from './components/mb-component/MeritBankApp';


class App extends Component {
  render() {
    return (
      <div className="App">
          {/*<Counter />*/}
          <MeritBankApp />
          
      </div>
      
    );
  }
}

class LearningComponent extends Component {
  render() {
    return (
      <div className="App">
        My Hello World !!!
        <HeaderComponent></HeaderComponent>
        <LoginComponent></LoginComponent>
        <FooterComponent></FooterComponent>
      </div>
      
    );
  }
}
export default App;