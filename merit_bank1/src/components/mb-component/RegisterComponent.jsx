import React, { Component } from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import MeritBankService from '../../api/merit-bank/MeritBankService';

class RegisterComponent extends Component {
  
  constructor(props) {
    super(props)
    this.state = {
      firstName : '',
      middleName: '',
      lastName: '',
      ssn: '',
      username : '',
      password: '',
      email: ''
    }

    this.onSubmit = this.onSubmit.bind(this)
    this.validate = this.validate.bind(this)
  }

      onSubmit(values) {
        console.log('Values are :: '+values);
        //let username = AuthenticationService.getLoggedInUserName()

        let accountHolder = {
          firstName: values.firstName,
          middleName: values.middleName,
          lastName: values.lastName,
          ssn: values.ssn,
          username : values.username,
          password: values.password,
          email: values.email
        }

        //if (this.state.id === -1) {
        MeritBankService.createAccountHolder(accountHolder)
                .then(() => this.props.history.push('/login'))
        //} 
        // else {
        //     TodoDataService.updateTodo(username, this.state.id, todo)
        //         .then(() => this.props.history.push('/todos'))
        // }

        console.log(values);
        //this.props.history.push('/login')
    }

    validate(values) {
      console.log('From validate - Values are :: '+values.firstName);

      
      let errors = {}
      if (!values.firstName) {
          errors.firstName = 'Enter first name'
      } 
      if (!values.middleName) {
        errors.middleName = 'Enter middle name'
    } 
      if (!values.lastName) {
        errors.lastName = 'Enter last name'
    } 
      if (!values.ssn) {
        errors.ssn = 'Enter SSN'
    } 
      if (!values.username) {
        errors.username = 'Enter user name'
    } 
    if (!values.password) {
      errors.password = 'Enter password'
    } 
    if (!values.email) {
      errors.email = 'Enter email'
    } 
      // else if (values.description.length < 5) {
      //     errors.description = 'Enter atleast 5 Characters in Description'
      // }

      // if (!moment(values.targetDate).isValid()) {
      //     errors.targetDate = 'Enter a valid Target Date'
      // }

      return errors;

    }
  
  render() {
    
    let { firstName, middleName,lastName, ssn, username, password, email } = this.state
    
    return (
        <div className="container">
          

          <Formik
                        initialValues={{ firstName, middleName,lastName, ssn, username, password, email }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="firstName" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="middleName" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="lastName" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="ssn" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="username" component="div"
                                        className="alert alert-warning" />
                                    <ErrorMessage name="password" component="div"
                                        className="alert alert-warning" />
                                     <ErrorMessage name="email" component="div"
                                        className="alert alert-warning" />
                                    
                                    <fieldset className="form-group">
                                        <label>First Name</label>
                                        <Field className="form-control" type="text" name="firstName" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Middle Name</label>
                                        <Field className="form-control" type="text" name="middleName" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Last Name</label>
                                        <Field className="form-control" type="text" name="lastName" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>SSN</label>
                                        <Field className="form-control" type="text" name="ssn" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>User Name</label>
                                        <Field className="form-control" type="text" name="username" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Password</label>
                                        <Field className="form-control" type="text" name="password" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Email</label>
                                        <Field className="form-control" type="text" name="email" />
                                    </fieldset>

                                    <button className="btn btn-success" type="submit">Save</button>
                                </Form>
                            )
                        }
                    </Formik>
          
         
  
        </div>
  
      );
    }
  }

  export default RegisterComponent