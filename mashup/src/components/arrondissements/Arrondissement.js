import React, { Component } from 'react';
import ActivityList from '../activities/ActivityList'

class Arrondissement extends Component {

  constructor(props) {
    super(props);
    this.state = {
      uri : "",
      activities : []
    }  
  }

  searchActivities = (uri) => {
    const encodedQuery = encodeURIComponent(`PREFIX : <http://www.semanticweb.org/colettedupuy/ontologies/2021/film_paris#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    
    SELECT * WHERE {
      ?uri a :activite .
      ?uri rdfs:label ?label .
      ?uri :a_lieu_a <${uri}>
    }
    LIMIT 25
    `);
    const apiUrl = `http://localhost:3030/FilmParis/query?query=${encodedQuery}`;
    fetch(apiUrl)
      .then((response) => response.json())
      .then((data) => { 
        this.setState({activities: data.results.bindings, uri: uri})
        return true
      })
  }

  componentDidMount() {
    const { uri } = this.props.location.state
    this.searchActivities(uri)
  }

  render() {
    return (
        <div>
          <ActivityList activities={this.state.activities} arrondissement={this.state.uri} />
        </div>
    );
  }
}

export default Arrondissement;
