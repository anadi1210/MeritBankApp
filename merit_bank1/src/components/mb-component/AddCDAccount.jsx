import React, { Component } from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';

import MeritBankService from '../../api/merit-bank/MeritBankService';
import AuthenticationService from './AuthenticationService';

class AddCDAccount extends Component {
    constructor(props) {
        super(props)
        this.state = {
            amount : ''        
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)

    }

    onSubmit(values) {
        let CDAccount = {
            balance : values.amount,
            cdOffering : {
                cdOffereing_Id : 3
            }
        }
        let username = AuthenticationService.getLoggedInUsername()
      
        MeritBankService.createCDAccount(username, CDAccount)
                .then(() => this.props.history.push('/todos'))
     
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
            <h1> Add CD Account</h1>
           <hr/>
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

export default AddCDAccount