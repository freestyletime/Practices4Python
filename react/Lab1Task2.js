import React, { Component } from "react";
import { carParkObjectsArray } from "./carparking";

// function num_occupants_filter filters property of num_occupants
function num_occupants_filter(num) {
  return function (c) {
    if (c.num_occupants <= num) return c;
  };
}

class App extends Component {
  render() {
    let cars = carParkObjectsArray;

    return (
      <div className="App">
        <div className="PART A">
          <h3>
            [TASK 2 PART A] Write a map function which renders or prints out
            every object in the array.
          </h3>

          {cars.map((c) => (
            <div key={c.parking_id}>
              <p>
                <div>parking_id: {c.parking_id}</div>
                <div>car_reg: {c.car_reg}</div>
                <div>num_occupants: {c.num_occupants}</div>
                <div>parking_day: {c.parking_day}</div>
                <div>parking_hours: {c.parking_hours}</div>
              </p>
            </div>
          ))}
        </div>
        <div className="PART B">
          <h3>
            [TASK PART B] Write a filter function which only prints out or
            renders objects in the ��carParkObjectsArray�� array by filtering
            based on the num_occupants property.
          </h3>

          {cars.filter(num_occupants_filter(4)).map((c) => (
            <div key={c.parking_id}>
              <p>
                <div>parking_id: {c.parking_id}</div>
                <div>car_reg: {c.car_reg}</div>
                <div>num_occupants: {c.num_occupants}</div>
                <div>parking_day: {c.parking_day}</div>
                <div>parking_hours: {c.parking_hours}</div>
              </p>
            </div>
          ))}
        </div>
      </div>
    );
  }
}
export default App;
