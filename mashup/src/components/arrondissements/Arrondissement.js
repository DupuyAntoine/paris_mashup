import React, { Component } from 'react';
import ActivityList from '../activities/ActivityList'
import FilmList from '../films/FilmList'

class Arrondissement extends Component {

  constructor(props) {
    super(props);
    this.state = {
      uri : "",
      label : "",
      activities : [],
      films : []
    }  
  }

  searchFilms = (uri) => {
    const encodedQuery = encodeURIComponent(`PREFIX : <http://www.semanticweb.org/colettedupuy/ontologies/2021/film_paris#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    
    SELECT * WHERE {
      ?uri a :film .
      ?uri rdfs:label ?label .
      ?uri :a_ete_tourne_a <${uri}>
      BIND(RAND() AS ?random)
    }
    ORDER BY ?random
    LIMIT 25
    `);
    const apiUrl = `http://localhost:3030/FilmParis/query?query=${encodedQuery}`;
    fetch(apiUrl)
      .then((response) => response.json())
      .then((data) => { 
        this.setState({films: data.results.bindings, uri: uri})
        return true
      })
  }

  searchActivities = (uri) => {
    const encodedQuery = encodeURIComponent(`PREFIX : <http://www.semanticweb.org/colettedupuy/ontologies/2021/film_paris#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    
    SELECT * WHERE {
      ?uri a :activite .
      ?uri rdfs:label ?label .
      OPTIONAL { ?uri :a_lieu_a_partir_du ?date . }
      OPTIONAL { ?uri :a_pour_url_image ?url_image .}
      ?uri :a_lieu_a <${uri}>
      BIND(RAND() AS ?random)
    }
    ORDER BY ?random
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
    const { uri, label } = this.props.location.state
    this.searchActivities(uri)
    this.searchFilms(uri)
    this.setState({label})
  }

  render() {
    return (
        <div className="container" style={{color: 'white'}}>
          <h2>Activités dans l'arrondissement {this.state.label}</h2>
          <ActivityList activities={this.state.activities} arrondissement={this.state.uri} />
          <hr />
          <h2>Tournés dans l'arrondissement {this.state.label}</h2>
          <FilmList films={this.state.films} arrondissement={this.state.uri} />
        </div>
    );
  }
}

export default Arrondissement;
