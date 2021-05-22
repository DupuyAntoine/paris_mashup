import React, { Component } from 'react';
import { Link } from "react-router-dom";

class ArrondissementList extends Component {
  render() {
    return (
        <div>
          <ul>
            {this.props.arrondissements.map(arr => {return <li key={arr.uri.value.split('#')[1]}><Link to={
                {
                    pathname: "/arrondissement/" + arr.uri.value.split('#')[1],
                    state: {
                        label: arr.label.value,
                        uri: arr.uri.value
                    }
                }
            }>{arr.label.value}</Link></li>})}
          </ul>
        </div>
    );
  }
}

export default ArrondissementList;
