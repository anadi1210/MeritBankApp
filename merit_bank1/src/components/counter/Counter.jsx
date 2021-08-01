import React, { Component } from 'react';
import './Counter.css';
import PropTypes from 'prop-types';

 
class Counter extends Component {
    
    constructor() {
        super(); // Error 1
        this.state = {
            counter : 0
            
        }
        this.increment = this.increment.bind(this);
        this.decrement = this.decrement.bind(this);
        this.reset = this.reset.bind(this);
    }
    
    
    
    render() {
        return(
            <div className="counter">
                <CounterButton by={1} incrementMethod={this.increment} decrementMethod={this.decrement}></CounterButton>
                <CounterButton by={5} incrementMethod={this.increment} decrementMethod={this.decrement}></CounterButton>
                <CounterButton by={10} incrementMethod={this.increment} decrementMethod={this.decrement}></CounterButton>
                <span className="count">{this.state.counter}</span>
                <div><button className="reset" onClick={this.reset}> Reset</button></div>
            </div>
        );
    }

    reset() {
        this.setState( {counter: 0} );//+ this.props.by
            
        
    }

    increment(by) { // update state = counter++
        // console.log('increment');
         this.setState(
             (prevState) => {
                return {counter: prevState.counter + by} //+ this.props.by
             }
         );
     }

     decrement(by) { 
        this.setState(
            (prevState) => {
               return {counter: prevState.counter - by} //+ this.props.by
            }
        );
    }
   
}

class CounterButton extends Component {
   
   // Define the initial state in a constructor
   // state => counter 0
   
    constructor() {
        super(); // Error 1
        this.state = {
            counter : 0
            
        }

        this.increment = this.increment.bind(this);
        this.decrement = this.decrement.bind(this);
    }


    render() {
        return (
            <div className="counter">
                <button onClick={this.increment}>+{this.props.by}</button>
                <button onClick={this.decrement}>-{this.props.by}</button>
                {/*  <span className="count">{this.state.counter}</span> */}
            </div>
        );
    }

   increment() { // update state = counter++
       // console.log('increment');
        this.setState({
            counter : this.state.counter + this.props.by
        });

        this.props.incrementMethod(this.props.by);
    }

    decrement() { 
         this.setState({
             counter : this.state.counter - this.props.by
         });
 
         this.props.decrementMethod(this.props.by);
     }
}

CounterButton.defaultProps = {
    by : 1
}

CounterButton.propTypes = {
    by : PropTypes.number
}

export default Counter;