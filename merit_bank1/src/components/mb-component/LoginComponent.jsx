import React, { Component } from 'react';
import AuthenticationService from './AuthenticationService';


class LoginComponent extends Component {
  
    constructor(props){
      super(props);
  
      this.state = {
        username : '',
        password : '',
        hasLoginFailed:false,
        showSuccessMessage: false
      }
  
      // this.handleUsernameChange = this.handleUsernameChange.bind(this);
      // this.handlePasswordChange = this.handlePasswordChange.bind(this);
  
      this.handleChange = this.handleChange.bind(this);
      this.handleLoginButton = this.handleLoginButton.bind(this);
      this.handleRegisterButton = this.handleRegisterButton.bind(this);
    }
  
    handleChange(event) {
     // console.log(this.state)
      this.setState( {
            [event.target.name] : event.target.value
      }
  
      )
    }
  
  
    // handleUsernameChange(event) {
    //   //console.log(event.target.value)
    //   this.setState( {
    //     username : event.target.value
    //   }
  
    //   )
    // }
  
    // handlePasswordChange(event) {
    //   this.setState( {
    //     password : event.target.value
    //   }
  
    //   )
    // }
  
    handleLoginButton(){ 
        // Hardcoded validation with username = anadi and password=123
        // if(this.state.username==='anadi' && this.state.password==='123'){
        //   AuthenticationService.registerSuccessfulLogin(this.state.username,this.state.password );
        //   this.props.history.push(`/welcome/${this.state.username}`)
          
        //   //console.log('login success')
        //   // this.setState(
        //   //   {
        //   //     hasLoginFailed : false,
        //   //     showSuccessMessage : true
        //   //   }
        //   //   )
        // }
        // else {
        //   this.setState(
        //     {
        //       hasLoginFailed : true,
        //       showSuccessMessage : false
        //     }
        //     )
        //   console.log('login failed')
        // }

        AuthenticationService
        .executeJwtAuthenticationService(this.state.username, this.state.password)
        .then((response) => {
              AuthenticationService.registerSuccessfulLoginForJwt(this.state.username, response.data.token );
              //this.props.history.push(`/welcome/${this.state.username}`)   
              this.props.history.push(`/todos`)   
            }
        ).catch( () => 
        this.setState(
          {
            hasLoginFailed : true,
            showSuccessMessage : false
          }
          )
        )
         
    }

    handleRegisterButton() {
      this.props.history.push("/register");
    }
    
    render() {
      return (
        <div >
          <h1>Login</h1>
            <div className="container">
  
              {/*<ShowInvalidCredentials hasLoginFailed={this.state.hasLoginFailed}/>*/}
              {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid creadentials</div>}
  
              {/*<ShowSuccessMessage showSuccessMessage={this.state.showSuccessMessage}/>*/}
              {this.state.showSuccessMessage && <div>Login Success</div>}
  
              User Name : <input type="text" name="username" value={this.state.username} onChange={this.handleChange}/> <br/><br/>
              Password : <input type="password" name="password" value={this.state.password} onChange={this.handleChange}/> <br/><br/>
              <button className="btn btn-success" onClick={this.handleLoginButton}>Login</button>
              &nbsp;
              <button className="btn btn-success" onClick={this.handleRegisterButton}>New User ?</button>
            </div>
        </div>
      );
    }
  }

  export default LoginComponent