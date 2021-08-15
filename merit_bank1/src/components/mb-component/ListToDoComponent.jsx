
import React, { Component } from 'react';
import MeritBankService from '../../api/merit-bank/MeritBankService';
import moment from 'moment';

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
         welcomeMessage : ''
      }
      this.handleError = this.handleError.bind(this);
    }

    componentDidMount() {
        MeritBankService.retrieveAccountHolder()
        .then(
            response => {
              console.log(response)
              this.setState({
                   accountHolder : response.data[0],
                   checkingAccount : response.data[0].checkingAccount,
                  
                   savingsAccount : response.data[0].savingsAccounts,
                   cdAccounts : response.data[0].cdAccountsList,
                  user_name : response.data[0].firstName
              })
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
                          
                              <tr key={this.state.checkingAccount.checkingAccountId}>
                                  <td>Checking Account</td>
                                  {/* <td>{this.state.user_name}</td> */}
                                 
                                  <td>{this.state.checkingAccount.balance}</td>
                                  <td>{moment(this.state.checkingAccount.openedOn).format('YYYY-MM-DD')}</td>
                                  <td><button className="btn btn-success">Deposit</button></td>
                                  <td><button className="btn btn-primary">Withdraw</button></td>
                                  <td><button className="btn btn-warning">Transfer</button></td>
                                  <td><button className="btn btn-danger">Delete</button></td>
                              </tr>
                          
                         }
                          {
                             <tr key={this.state.savingsAccount.savingsAccountId}>
                             <td>Savings Account</td>
                             {/* <td>{this.state.user_name}</td> */}
                            
                             <td>{this.state.savingsAccount.balance}</td>
                             <td>{this.state.savingsAccount.openedOn}</td>
                             <td><button className="btn btn-success">Deposit</button></td>
                             <td><button className="btn btn-primary">Withdraw</button></td>
                             <td><button className="btn btn-warning">Transfer</button></td>
                             <td><button className="btn btn-danger">Delete</button></td>
                           </tr>
                          }
                          {
                            this.state.cdAccounts.map(
                              cdAcc => 
                             <tr key={cdAcc.cdAccountId}>
                               <td>CD Account</td>
                               <td>{cdAcc.balance}</td>
                               <td>{cdAcc.openedOn}</td>
                               <td><button className="btn btn-success">Deposit</button></td>
                             <td><button className="btn btn-primary">Withdraw</button></td>
                             <td><button className="btn btn-warning">Transfer</button></td>
                             <td><button className="btn btn-danger">Delete</button></td>
                             </tr>
                            )
                          }






                      </tbody>
                    </table>
                </div>
            </div>
      );
    }
  }

  export default ListToDoComponent