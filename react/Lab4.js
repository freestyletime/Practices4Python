import React, { Component } from "react";
import { stockArray } from "./stocks.js";

const localStocks = stockArray;

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { buying: [], selling: [], sArray: localStocks };
  } // end constructor

  sortPrice(f, s) {
    let comparison = 0;
    if (f.Price > s.Price) comparison = -1;
    else if (f.Price < s.Price) comparison = 1;
    else comparison = 0;
    return comparison;
  }

  total() {
    return function (f, s) {
      return f.Price + s.Price;
    };
  }

  filterObj(symbol) {
    return function (obj) {
      return obj.Symbol === symbol;
    };
  }

  add(symbol) {
    let s = this.state.sArray.filter(this.filterObj(symbol));
    this.setState({ buying: this.state.buying.concat(s) });
  }

  delete(symbol) {
    let s = this.state.sArray.filter(this.filterObj(symbol));
    this.setState({ selling: this.state.selling.concat(s) });
  }

  clearB() {
    this.setState({ buying: [] });
  }

  clearS() {
    this.setState({ selling: [] });
  }

  render() {
    return (
      <div className="App">
        <h1>CS385 Stocks and Shares</h1>
        <ul>
          {this.state.sArray.map((s) => (
            <li key={s.Symbol}>
              <b>{s.Symbol}</b>, <i>{s.Company}</i> ${s.Price}
              <button onClick={() => this.add(s.Symbol)}>BUY</button>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <button onClick={() => this.delete(s.Symbol)}>SELL</button>
            </li>
          ))}
        </ul>
        <hr />
        <p>
          Total stock objects (BUY): {this.state.buying.length}&nbsp;&nbsp;
          <button onClick={() => this.clearB()}>EMPTY BUYING</button>
          <br />
          Total stock prices (BUY):&nbsp;
          {this.state.buying.reduce(function (result, item) {
            return result + item.Price;
          }, 0)}
          <ol>
            {this.state.buying.sort(this.sortPrice).map((s) => (
              <li key={s.Symbol}>
                <b>{s.Symbol}</b>, <i>{s.Company}</i> ${s.Price}
              </li>
            ))}
          </ol>
          <br />
          Total stock objects (SELL): {this.state.selling.length}&nbsp;&nbsp;
          <button onClick={() => this.clearS()}>EMPTY SELLING</button>
          <br />
          Total stock prices (SELL):&nbsp;
          {this.state.selling.reduce(function (result, item) {
            return result + item.Price;
          }, 0)}
          <ol>
            {this.state.selling.sort(this.sortPrice).map((s) => (
              <li key={s.Symbol}>
                <b>{s.Symbol}</b>, <i>{s.Company}</i> ${s.Price}
              </li>
            ))}
          </ol>
        </p>
      </div>
    ); // end of return statement
  } // end of render function
} // end of class

export default App;