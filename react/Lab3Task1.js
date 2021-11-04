import React, { Component } from "react";
import { spotifyArray } from "./spotify.js";

const musics = spotifyArray;

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { searchKey: "", data: musics };
    this.textChangeCallback = this.textChangeCallback.bind(this);
  } // end constructor

  textChangeCallback(event) {
    this.setState({ searchKey: event.target.value });
  }

  render() {
    return (
      <div className="App">
        <h1>CS385 Spotify Search App</h1>
        <ComponentA textChangeCallback={this.textChangeCallback} />
        <ComponentB searchItem={this.state.searchKey} data={this.state.data} />
      </div>
    ); // end of return statement
  } // end of render function
} // end of class

//**************************************************//
class ComponentA extends Component {
  render() {
    const textChangeCallback = this.props.textChangeCallback;

    return (
      <div className="ComponentA">
        <hr />
        <h3>Search Box</h3>
        <form>
          <label for="fname">search music:</label>
          <br />
          <input type="text" onChange={textChangeCallback}></input>
          <br />
        </form>
      </div>
    );
  }
} // close the ComponentA component
//**************************************************//
class ComponentB extends Component {
  music_filter(searchText) {
    return function (item) {
      let title = item.title.toLowerCase();
      let artist = item.artist.toLowerCase();
      let topgenre = item.topgenre.toLowerCase();
      return (
        searchText !== "" &&
        (title.includes(searchText.toLowerCase()) ||
          artist.includes(searchText.toLowerCase()) ||
          topgenre.includes(searchText.toLowerCase()))
      );
    };
  }

  render() {
    const myKey = this.props.searchItem;
    const musics = this.props.data;

    let count = musics.filter(this.music_filter(myKey)).length;

    return (
      <div className="ComponentB">
        <hr />
        <h3>Search Result</h3>
        Number of results find {count}
        {musics.filter(this.music_filter(myKey)).map((a) => (
          <div id={a.ID}>
            <p>
              <hr />
              <div>ID: {a.ID}</div>
              <div>title: {a.title}</div>
              <div>artist: {a.artist}</div>
              <div>topgenre: {a.topgenre}</div>
              <div>year: {a.year}</div>
            </p>
          </div>
        ))}
      </div>
    );
  }
} // close the ComponentB component

export default App;