import React, { Component } from "react";

class App extends Component {
  // create this.state with our variable(s)
  // This is a constructor. We always
  // declare our state variables here.

  constructor(props) {
    super(props);

    this.state = { number: 1 };
    this.forwardButtionCallback = this.forwardButtionCallback.bind(this);
    this.resetButtonCallback = this.resetButtonCallback.bind(this);
  } // end constructor

  forwardButtionCallback() {
    let num = this.state.number;
    if (num % 2 === 0) num += 5;
    else num += 7;
    this.setState({ number: num });
  }

  resetButtonCallback() {
    this.setState({ number: 1 });
  }

  render() {
    return (
      <div className="App">
        This is the parent-level component. <br />
        The current value for <b>this.state.number</b>
        is {this.state.number}. You will need to add the Forward and Reset
        component to this Component (the parent)
        <hr />
        <Forward clickCallback={this.forwardButtionCallback} />
        <hr />
        <Reset clickCallback={this.resetButtonCallback} />
      </div>
    ); // end of return statement
  } // end of render function
} // end of class

//==================== Start a new component class
// This component class is called Forward

class Forward extends Component {
  render() {
    const clickCallback = this.props.clickCallback;

    return (
      <div className="ForwardComponent">
        <h2>This is the Forward component</h2> <br />
        This is a child-level component <br />
        <button type="button" onClick={clickCallback}>
          Forward
        </button>
      </div>
    ); // end of return statement
  } // end of render function
} // end of class

//=================== Start a new component class
// This component class is called Reset
class Reset extends Component {
  render() {
    const clickCallback = this.props.clickCallback;

    return (
      <div className="ResetComponent">
        <h2>This is the Reset component.</h2> <br />
        This is a child-level component <br />
        <button type="reset" onClick={clickCallback}>
          Reset
        </button>
      </div>
    ); // end of return statement
  } // end of render function
} // end of class

export default App;