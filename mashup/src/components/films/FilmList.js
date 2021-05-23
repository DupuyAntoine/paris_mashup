import React, { Component } from 'react';

class FilmList extends Component {
  render() {
    return (
        <div>
          <ul>
            {this.props.films.map(act => {return <li key={act.uri.value.split('#')[1]}>{act.label.value}</li>})}
          </ul>
        </div>
    );
  }
}

export default FilmList;
