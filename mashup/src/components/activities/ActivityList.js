import React, { Component } from 'react';
import { Link } from "react-router-dom";

class ActivityList extends Component {
  render() {
    return (
        <div>
          <ul>
            {this.props.activities.map(act => {return <li key={act.uri.value.split('#')[1]}><Link to={
                {
                    pathname: "/activity/" + act.uri.value.split('#')[1],
                    state: {
                        label: act.label.value,
                        uri: act.uri.value,
                        arrondissement : this.props.arrondissement
                    }
                }
            }>{act.label.value}</Link></li>})}
          </ul>
        </div>
    );
  }
}

export default ActivityList;
