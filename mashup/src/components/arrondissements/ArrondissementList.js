import React, { Component } from 'react';
import { Link } from "react-router-dom";
import { Button, Card, CardDeck } from 'react-bootstrap'

class ArrondissementList extends Component {
  render() {
    return (
      <CardDeck style={{display: 'flex', flexDirection: 'row', color: 'black'}}>
        {this.props.arrondissements.map(arr => {
          return <Card style={{ width: '18rem', marginRight: '20px', flex: 1, marginTop: '50px', height: 'max-content' }} key={arr.label.value}>
            <Card.Body>
              <Card.Title>{arr.label.value}</Card.Title>
              <Card.Text>
                {arr.name.value}
              </Card.Text>
              <Link to={
                {
                    pathname: "/arrondissement/" + arr.uri.value.split('#')[1],
                    state: {
                        label: arr.label.value,
                        uri: arr.uri.value
                    }
                }
              }>
                <Button variant="secondary">Plus d'infos</Button>
              </Link>
            </Card.Body>
          </Card>})
        }
      </CardDeck>
    );
  }
}

export default ArrondissementList;
