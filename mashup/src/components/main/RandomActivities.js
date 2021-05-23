import React, { Component } from 'react';
import { Card, CardColumns } from 'react-bootstrap'

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
      OPTIONAL { ?uri :a_lieu_a_partir_du ?date . }
      OPTIONAL { ?uri :a_pour_url_image ?url_image .}
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
    <CardColumns style={{display: 'flex', flexWrap: 'wrap'}}>
      {this.state.activities.map(act => {
        return <Card className="text-center" style={{ width: '18rem', minWidth: '18rem', marginRight: '20px', flex: 0, marginTop: '50px' }} key={act.label.value}>
          <Card.Img variant="top" src={act.url_image ? act.url_image.value : "../téléchargement.svg"} />
          <Card.Title>{act.label.value}</Card.Title>
          <Card.Text>{act.date ? new Date(act.date.value).toLocaleDateString("fr-FR") : ""}</Card.Text>
        </Card>
      })}
    </CardColumns>
        </div>
    );
  }

}

export default RandomActivities;