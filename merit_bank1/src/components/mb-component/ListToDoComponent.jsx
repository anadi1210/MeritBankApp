
import React, { Component } from 'react';
import MeritBankService from '../../api/merit-bank/MeritBankService';
import moment from 'moment';
import AuthenticationService from './AuthenticationService';

class ListToDoComponent extends Component {
  
    constructor(props) {
      super(props);
  
      this.state = {
        todos : 
          [
            //  {id: 1, description: 'Learn React', done: false, targetDate: new Date()},
            //  {id: 2, description: 'Learn to Dance', done: false, targetDate: new Date()},
            //  {id: 3, description: 'Visit India', done: false, targetDate: new Date()},
            //  {id: 4, description: 'Play cricket', done: false, targetDate: new Date()}
          ]  ,
          accountHolder : {},
          checkingAccount : {
           
          } ,
          savingsAccount : {
             
          },
         cdAccounts : [],
         user_name : '',
         welcomeMessage : '',
         hasCheckingAccount : false,
         hasSavingsAccount : false,
         hasCDAccount : false
      }
      this.handleError = this.handleError.bind(this);
      this.addCheckingButton = this.addCheckingButton.bind(this)
      this.addSavingsButton = this.addSavingsButton.bind(this)
      this.addCDButton = this.addCDButton.bind(this)
      this.handleDeposit = this.handleDeposit.bind(this)
      this.handleWithdraw = this.handleWithdraw.bind(this)
      this.handleTransfer = this.handleTransfer.bind(this)
      this.handleDeleteAccount = this.handleDeleteAccount.bind(this)
    }

    componentDidMount() {
        const username = AuthenticationService.getLoggedInUsername()
       
        MeritBankService.retrieveAccountHolder(username)
        .then(
            response => {
              console.log(response)
              this.setState({
                   accountHolder : response.data,
                   checkingAccount : response.data.checkingAccount,
                  
                   savingsAccount : response.data.savingsAccounts,
                   cdAccounts : response.data.cdAccountsList,
                  user_name : response.data.firstName
              })

              if(response.data.checkingAccount){
                this.setState({hasCheckingAccount : true})
              }
              if(response.data.savingsAccounts){
                this.setState({hasSavingsAccount : true})
              }
              if(response.data.cdAccountsList){
                this.setState({hasCDAccount : true})
              }
            }
           
        )
        .catch(error => this.handleError(error))
        
    }



    handleError(error) {
      console.log("This is error message :: "+error.message)
     
      let errorMessage : "";

      if(error.message ){
        errorMessage += error.message
      }

      if(error.response && error.response.data) {
        errorMessage += error.response.data.message
      }
      
      this.setState( { welcomeMessage : error.message } )

      }

      addCheckingButton() {
        this.props.history.push("/addCheckingAccount")
      }

      addSavingsButton(){
        this.props.history.push("/addSavingsAccount")
      }

      addCDButton() {
        this.props.history.push("/addCDAccount")
      }
    
      handleDeposit(event) {
        let accountid = event.target.value
        this.props.history.push(`/deposit/${accountid}`)
      }
      handleWithdraw(event) {
        let accountid = event.target.value
        this.props.history.push(`/withdraw/${accountid}`)
      }
      handleTransfer(event) {
        let accountid = event.target.value
        this.props.history.push(`/transfer/${accountid}`)
      }
      handleDeleteAccount(event) {
        let accountid = event.target.value
        
      }

    render() {
      return (
            <div >
                <h3>Welcome {this.state.user_name}</h3>
                <h4>Accounts summary</h4>
                <div className="container">
                    <table className="table"> 
                      <thead>
                        <tr>
                          <th>Account Type</th>
                          <th>Account Balance</th>
                          <th>Account open date</th>
                          <th>Deposit</th>
                          <th>Withdraw</th>
                          <th>Transfer</th>
                          <th>Delete Account</th>
                        </tr>
                      </thead>
                      <tbody>
                        
                          {
                            
                            // Commenting Working code
                            // this.state.todos.map (
                            //   todo => 
                            //       // <tr key={todo.id}>
                            //       //   <td>{todo.id}</td>
                            //       //   <td>{todo.description}</td>
                            //       //   <td>{todo.done.toString()}</td>
                            //       //   <td>{todo.targetDate.toString()}</td>
                            //       // </tr>
                            //       <tr key={todo.accountHolderId}>
                            //       <td>{todo.accountHolderId}</td>
                            //       <td>{todo.firstName}</td>
                            //       <td>{todo.lastName}</td>
                            //       <td>{todo.username}</td>
                            //      {/* {(todo.checkingAccount) &&  <td>'Checking Account'</td>} */}
                            //       {/* {(todo.savingsAccounts) && <td>'Savings Account'</td>} */}
                            //       <td><button className="btn btn-warning">Delete</button></td>
                            //     </tr>
                            // )
                          }  
                            
                            {
                              this.state.hasCheckingAccount &&
                              
                              <tr key={this.state.checkingAccount.id}>
                                  <td>Checking Account</td>
                                  {/* <td>{this.state.user_name}</td> */}
                                 
                                  <td>{this.state.checkingAccount.balance}</td>
                                  <td>{moment(this.state.checkingAccount.openedOn).format('YYYY-MM-DD')}</td>
                                  <td><button className="btn btn-success" onClick={this.handleDeposit} value={this.state.checkingAccount.id}>Deposit</button></td>
                                  <td><button className="btn btn-primary" onClick={this.handleWithdraw} value={this.state.checkingAccount.id}>Withdraw</button></td>
                                  <td><button className="btn btn-warning" onClick={this.handleTransfer} value={this.state.checkingAccount.id}>Transfer</button></td>
                                  <td><button className="btn btn-danger" onClick={this.handleDeleteAccount} value={this.state.checkingAccount.id}>Delete</button></td>
                              </tr>
                          
                         }
                          {
                            this.state.hasSavingsAccount && 
                             <tr key={this.state.savingsAccount.id}>
                             <td>Savings Account</td>
                             {/* <td>{this.state.user_name}</td> */}
                            
                             <td>{this.state.savingsAccount.balance}</td>
                             <td>{moment(this.state.savingsAccount.openedOn).format('YYYY-MM-DD')}</td>
                             <td><button className="btn btn-success" onClick={this.handleDeposit} value={this.state.savingsAccount.id}>Deposit</button></td>
                             <td><button className="btn btn-primary" onClick={this.handleWithdraw} value={this.state.savingsAccount.id}>Withdraw</button></td>
                             <td><button className="btn btn-warning" onClick={this.handleTransfer} value={this.state.savingsAccount.id}>Transfer</button></td>
                             <td><button className="btn btn-danger" onClick={this.handleDeleteAccount} value={this.state.savingsAccount.id}>Delete</button></td>
                           </tr>
                          }
                          {
                            this.state.hasCDAccount && 
                            this.state.cdAccounts.map(
                              cdAcc => 
                             <tr key={cdAcc.id}>
                                <td>CD Account</td>
                                <td>{cdAcc.balance}</td>
                                <td>{moment(cdAcc.openedOn).format('YYYY-MM-DD')}</td>
                                <td><button className="btn btn-success" disabled>Deposit</button></td>
                                <td><button className="btn btn-primary" disabled>Withdraw</button></td>
                                <td><button className="btn btn-warning" disabled>Transfer</button></td>
                                <td><button className="btn btn-danger" onClick={this.handleDeleteAccount} value={cdAcc.id}>Delete</button></td>
                             </tr>
                            )
                          }

                          {
                            !(this.state.hasCheckingAccount) && <button className="btn btn-success" onClick={this.addCheckingButton}>Add Checking Account</button>
                          }
                          <hr/>
                          { 
                            !(this.state.hasSavingsAccount) &&  <button className="btn btn-success" onClick={this.addSavingsButton}>Add Savings Account</button>
                          }
                          <hr/>
                          {
                            <button className="btn btn-success" onClick={this.addCDButton}>Add CD Account</button>
                          }
                           
                        




                      </tbody>
                    </table>
                </div>
            </div>
      );
    }
  }

  export default ListToDoComponent