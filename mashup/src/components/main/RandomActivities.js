import React, { Component } from 'react';

class RandomActivities extends Component {

  constructor(props) {
    super(props);
    this.state = {
      activities : []
    }  
  }

  searchActivities = () => {
    const encodedQuery = encodeURIComponent(`PREFIX : <http://www.semanticweb.org/colettedupuy/ontologies/2021/film_paris#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    
    SELECT * WHERE {
      ?uri a :activite .
      ?uri rdfs:label ?label .
      BIND(RAND() AS ?random)
    }
    ORDER BY ?random
    LIMIT 25
    `);
    const apiUrl = `http://localhost:3030/FilmParis/query?query=${encodedQuery}`;
    fetch(apiUrl)
      .then((response) => response.json())
      .then((data) => { 
        this.setState({activities: data.results.bindings})
        return true
      })
  }

  componentDidMount() {
    this.searchActivities()
  }

  render() {
    return (
        <div>
          <ul>
            {this.state.activities.map(act => {return <li key={act.uri.value.split('#')[1]}>{act.label.value}</li>})}
          </ul>
        </div>
    );
  }

}

export default RandomActivities;