import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import HelloWorldService from '../../api/merit-bank/HelloWorldService';

class WelcomeComponent extends Component {
   
  constructor(props){
    super(props);

    this.retrieveWelcomeMessage = this.retrieveWelcomeMessage.bind(this);
    this.handleSuccessfulResponse = this.handleSuccessfulResponse.bind(this);
    this.handleError = this.handleError.bind(this);
    this.state = {
      welcomeMessage : '',
      errorMessage : ''
    }
  }
  render() {
      return (
        <>
          <h1>Welcome</h1>
          
         <div className="container">
            Welcome {this.props.match.params.name}.
            You can manage your ToDo's <Link to="/todos">here</Link>
         </div>

         <div className="container">
            Click here to get a Customized Message
            <button onClick={this.retrieveWelcomeMessage}>Get Welcome Message</button>
         </div>

         <div className="container">
            {this.state.welcomeMessage}
         </div>

         <div className="container">
            {this.state.errorMessage}
         </div>
  
        </>
  
      );
    }

    retrieveWelcomeMessage() {
      //console.log("Retrive Welcome Message method call");

      // HelloWorldService.executeHelloWorldService()
      // .then(response => this.handleSuccessfulResponse(response))
      // .catch((error => this.handleError(error))

      HelloWorldService.executeHelloWorldPathVariableService(this.props.match.params.name)
      .then(response => this.handleSuccessfulResponse(response))
      .catch(error => this.handleError(error))

    }

    handleSuccessfulResponse(response) {
      this.setState(
        {
          welcomeMessage : response.data.message
        }
      )
    }

    handleError(error) {
     console.log(error);
    
      this.setState(
        {
          errorMessage : error.response.data.message
        }
      )
     }

  }

  export default WelcomeComponent