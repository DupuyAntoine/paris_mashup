import React, { Component } from 'react';

class About extends Component {
  constructor(props) {
    super(props);
    this.state = {
      about : ""
    }  
  }

  componentDidMount() {
    const encodedQuery = encodeURIComponent(`PREFIX : <http://www.semanticweb.org/colettedupuy/ontologies/2021/film_paris#>
    SELECT * WHERE {
      :paris <http://dbpedia.org/ontology/abstract> ?a .
      FILTER(LANG(?a) = "fr")
    }`);
    const apiUrl = `http://localhost:3030/FilmParis/query?query=${encodedQuery}`;
    fetch(apiUrl)
      .then((response) => response.json())
      .then((data) => data.results.bindings.map((e) => {
        this.setState({about: e.a.value})
        return true
      }))
  }

  render() {
    return (
        <div>
          <h2>A propos de Paris</h2>
          <p>{this.state.about}</p>
        </div>
    );
  }
}

export default About;
