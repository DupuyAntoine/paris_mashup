import React, { Component } from 'react';
import FilmList from '../films/FilmList'

class RandomFilms extends Component {

  constructor(props) {
    super(props);
    this.state = {
      films : []
    }  
  }

  searchFilms = () => {
    const encodedQuery = encodeURIComponent(`PREFIX : <http://www.semanticweb.org/colettedupuy/ontologies/2021/film_paris#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    
    SELECT * WHERE {
      ?uri a :film .
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
        this.setState({films: data.results.bindings})
        return true
      })
  }

  componentDidMount() {
    this.searchFilms()
  }

  render() {
    return (
        <div>
          <FilmList films={this.state.films} />
        </div>
    );
  }

}

export default RandomFilms;