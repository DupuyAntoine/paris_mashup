import React, { Component } from 'react';
import { Table, Image } from 'react-bootstrap'

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
      OPTIONAL {<${uri}> :a_pour_catégorie ?cat .}
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
          console.log(elt)
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
        <div className="container" style={{height: '100vh', width: '100vw', color: 'white', textAlign: 'center'}}>
            <Image style={{marginBottom: '50px'}} src={this.state.informations.url_image ? this.state.informations.url_image.value : "../téléchargement.svg"} height="50%" width="50%" rounded />
            <Table striped hover variant="dark">
              <thead>
                <tr>
                  <th>Titre</th>
                  <th>Date de début</th>
                  <th>Catégorie</th>
                  <th>Accès</th>
                  <th>Gratuit ou payant</th>
                  <th>Site web</th>
                  <th>Accès mal entendant</th>
                  <th>Accès mal voyant</th>
                  <th>Accès Personne à Mobilité réduite</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>{this.state.informations.label.value}</td>
                  <td>{this.state.informations.date ? new Date(this.state.informations.date.value).toLocaleDateString('fr-FR') : "Non connue"}</td>
                  <td>{this.state.informations.cat ? this.state.informations.cat.value : "Non connue"}</td>
                  <td>{this.state.informations.acc ? this.state.informations.acc.value : "Non connu"}</td>
                  <td>{this.state.informations.prix ? this.state.informations.prix.value : "Non connu"}</td>
                  <td>{this.state.informations.url ? <a href={this.state.informations.url.value}>Lien</a> : "Non connu"}</td>
                  <td>{this.state.informations.accesme ? (this.state.informations.accesme.value === "0" ? "Non" : "Oui") : "Non connu"}</td>
                  <td>{this.state.informations.accesmv ? (this.state.informations.accesmv.value === "0" ? "Non" : "Oui") : "Non connu"}</td>
                  <td>{this.state.informations.accespmr ? (this.state.informations.accespmr.value === "0" ? "Non" : "Oui") : "Non connu"}</td>
                </tr>
              </tbody>
            </Table>         
        </div>
      );  
    } else {
      return <div><p>Loading...</p></div>
    }
  }
}

export default Activity;
