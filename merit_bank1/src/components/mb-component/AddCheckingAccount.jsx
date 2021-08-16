import React, { Component } from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';

import MeritBankService from '../../api/merit-bank/MeritBankService';
import AuthenticationService from './AuthenticationService';

class AddCheckingAccount extends Component {
    constructor(props) {
        super(props)
        this.state = {
            amount : ''        
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)

    }

    onSubmit(values) {
        console.log("Amount is :: "+values.amount)
        let checkingAccount = {
            balance : values.amount
        }
        let username = AuthenticationService.getLoggedInUsername()
      
        MeritBankService.createCheckingAccount(username, checkingAccount)
                .then(() => this.props.history.push('/todos'))
                console.log("Amount is :: "+checkingAccount.amount)
    }

    validate(values) {
        //console.log('From validate - Values are :: '+values.firstName); 
        let errors = {}
        if (!values.amount) {
            errors.amount = 'Enter amount'
        } 
        
        return errors;
  
      }



    render() {
        let { amount } = this.state

        return (
            <div className="container">
                <hr/>
            <h1> Add Checking Account</h1>
            <div className="container">
              <Formik
                            initialValues={{ amount }}
                            onSubmit={this.onSubmit}
                            validateOnChange={false}
                            validateOnBlur={false}
                            validate={this.validate}
                            enableReinitialize={true}
                        >
                            {
                                (props) => (
                                    <Form>
                                        <ErrorMessage name="amount" component="div"
                                            className="alert alert-warning" />
                                                                             
                                        <fieldset className="form-group">
                                            <label>Amount</label>
                                            <Field className="form-control" type="text" name="amount" />
                                        </fieldset>
                                        <button className="btn btn-success" type="submit">Save</button>
                                    </Form>
                                )
                            }
                        </Formik>
            </div>
            </div>
          );

    }

}

export default AddCheckingAccount