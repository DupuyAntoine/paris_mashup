import React, { Component } from 'react';

class Activity extends Component {

  constructor(props) {
    super(props);
    this.state = {
      informations: {}
    }  
  }

  isReady = () => {
    return (this.state.informations.label !== undefined)
  }

  getActivityInformation = (uri, arrondissement) => {
    const encodedQuery = encodeURIComponent(`PREFIX : <http://www.semanticweb.org/colettedupuy/ontologies/2021/film_paris#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
        
    SELECT * WHERE {
      <${uri}> a :activite .
      <${uri}> rdfs:label ?label .
      OPTIONAL {<${uri}> :a_lieu_a_partir_du ?date}
      OPTIONAL {<${uri}> :a_pour_adresse ?adr .}
      OPTIONAL {<${uri}> :a_pour_categorie ?cat .}
      OPTIONAL {<${uri}> :a_pour_type_accès ?acc .}
      OPTIONAL {<${uri}> :a_pour_type_de_prix ?prix .}
      OPTIONAL {<${uri}> :a_pour_url ?url .}
      OPTIONAL {<${uri}> :a_pour_url_image ?url_image .}
      OPTIONAL {<${uri}> :a_un_accès_mal_entendant ?accesme .}
      OPTIONAL {<${uri}> :a_un_accès_mal_voyant ?accesmv .}
      OPTIONAL {<${uri}> :a_un_accès_PMR ?accespmr .}
      <${uri}> :a_lieu_a <${arrondissement}>
    }
    `);
    const apiUrl = `http://localhost:3030/FilmParis/query?query=${encodedQuery}`;
    fetch(apiUrl)
      .then((response) => response.json())
      .then((data) => { 
        data.results.bindings.map((elt) => {
          this.setState({
            informations: elt
          })
          return true
        })
        return true
      })
  }

  componentDidMount() {
    const { uri, arrondissement } = this.props.location.state
    this.getActivityInformation(uri, arrondissement)
  }

  render() {
    if (this.isReady()) {
      return (
          <div>
              <h2>{this.state.informations.label.value}</h2>
          </div>
      );  
    } else {
      return <div><p>Loading...</p></div>
    }
  }
}

export default Activity;
