import React, { Component } from 'react';
import GenreInput from '../input/GenreInput';
import ArrondissementList from '../arrondissements/ArrondissementList';

class Home extends Component {

  constructor(props) {
    super(props);
    this.state = {
      arrondissements : []
    }  
  }

  searchArrondissements = (genre) => {
    const encodedQuery = encodeURIComponent(`PREFIX : <http://www.semanticweb.org/colettedupuy/ontologies/2021/film_paris#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    
    SELECT ?uri ?label (COUNT(?film) AS ?nbFilm) WHERE {
      ?uri a :arrondissement .
      ?uri rdfs:label ?label .
      ?film :a_ete_tourne_a ?uri .
      ?film :est_classe_comme :${genre.toLowerCase()}
    }
    GROUP BY ?uri ?label
    ORDER BY DESC(?nbFilm)
    LIMIT 3
    `);
    const apiUrl = `http://localhost:3030/FilmParis/query?query=${encodedQuery}`;
    fetch(apiUrl)
      .then((response) => response.json())
      .then((data) => { 
        this.setState({arrondissements: data.results.bindings})
        return true
      })
  }

  render() {
    return (
        <div>
          <h2>Découvrons ensemble par où vous commencerez votre visite !</h2>
          <GenreInput onSubmit={this.searchArrondissements} />
          <ArrondissementList arrondissements={this.state.arrondissements} />
        </div>
    );
  }
}

export default Home;
