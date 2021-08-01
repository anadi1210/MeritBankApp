import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch, } from 'react-router-dom';
import FooterComponent from '../common/FooterComponent';
import HeaderComponent from '../common/HeaderComponent';

import AuthenticatedRoute from './AuthenticatedRoute.jsx';
import LoginComponent from './LoginComponent.jsx';
import ListToDoComponent from './ListToDoComponent.jsx';
import WelcomeComponent from './WelcomeComponent.jsx';
import LogoutComponent from './LogoutComponent.jsx';
import ErrorComponent from './ErrorComponent.jsx';

class MeritBankApp extends Component {
  render() {
    return (
      <div className="MeritBankApp">
        
       <Router>
        
          <div>
            <HeaderComponent /><hr/>
            <Switch>
              <Route exact path="/" component={LoginComponent} />
              <Route exact path="/login" component={LoginComponent} />
              <AuthenticatedRoute exact path="/welcome/:name" component={WelcomeComponent} />
              <AuthenticatedRoute exact path="/todos" component={ListToDoComponent} />
              <AuthenticatedRoute exact path="/logout" component={LogoutComponent} />
              {/* <LoginComponent ></LoginComponent>
                    <WelcomeComponent></WelcomeComponent> */}

              <Route component={ErrorComponent} />
            </Switch>
            <hr  className="footer"/><FooterComponent />
          </div>
        </Router>
      </div>
    );
  }
}



// function ShowInvalidCredentials(props) {
//   if(props.hasLoginFailed){
//     return (
//       <div>Invalid creadentials</div>
//     )
//   }
//   return null
// }

// function ShowSuccessMessage(props) {
//   if(props.showSuccessMessage){
//     return (
//       <div>Login Success</div>
//     )
//   }
//   return null
// }

export default MeritBankApp;
