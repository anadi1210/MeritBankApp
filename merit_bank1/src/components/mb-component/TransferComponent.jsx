import React, { Component } from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';

import MeritBankService from '../../api/merit-bank/MeritBankService';
import AuthenticationService from './AuthenticationService';

class TransferComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            amount : '',
            targetAccountNumber : ''        
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)

    }

    onSubmit(values) {
        //console.log("Amount is :: "+values.amount)
        let accountNum = this.props.match.params.accountId
        console.log('accountNum is :: ' + accountNum)
        let transferTransaction = {
            amount : values.amount,
            sourceAccountNumber : accountNum,
           targetAccountNumber : values.targetAccountNumber
        }
        let username = AuthenticationService.getLoggedInUsername()
        
        MeritBankService.processTransfer(username, transferTransaction)
                .then(() => this.props.history.push('/todos'))
                //console.log("Amount is :: "+checkingAccount.amount)
    }

    validate(values) {
        //console.log('From validate - Values are :: '+values.firstName); 
        let errors = {}
        if (!values.amount) {
            errors.amount = 'Enter amount'
        } 
        if (!values.targetAccountNumber) {
            errors.targetAccountNumber = 'Enter beneficiary account number'
        } 
        
        return errors;
  
      }



    render() {
        let { amount, targetAccountNumber } = this.state
        
        return (
            <div className="container">
                <hr/>
            <h1> Transfer Funds </h1>
            <div className="container">
              <Formik
                            initialValues={{ amount , targetAccountNumber}}
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
                                        <ErrorMessage name="targetAccountNumber" component="div"
                                            className="alert alert-warning" />
                                                                             
                                        <fieldset className="form-group">
                                            <label>Amount</label>
                                            <Field className="form-control" type="text" name="amount" />
                                        </fieldset>
                                        <fieldset className="form-group">
                                            <label>Transfer to Account Number</label>
                                            <Field className="form-control" type="text" name="targetAccountNumber" />
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

export default TransferComponent