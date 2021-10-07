import React, { Component } from "react";

class App extends Component {
  render() {
    var alpha = 20;
    var beta = 100;
    var gamma = 1000;
    var x = "CS385";
    var y = "App Development";

    return (
      <div className="App">
        <p>
          The numerical value of the sum of alpha, beta and gamma:
          <b>{alpha + beta + gamma}</b>
        </p>

        <p>
          The numerical value of the product of alpha, beta and gamma:
          <b>{alpha * beta * gamma}</b>
        </p>

        <p>
          The numerical value of the sum of the three integer variables
          multiplied by 10:
          <b>{10 * (alpha + beta + gamma)}</b>
        </p>

        <p>
          The two strings x: <b>{x}</b> and y: <b>{y}</b>
        </p>

        <p>
          The string x in upper case. <b>{x.toUpperCase()} </b>
        </p>

        <p>
          The string y in lower case. <b>{y.toLowerCase()}</b>
        </p>
      </div>
    );
  }
}
export default App;
