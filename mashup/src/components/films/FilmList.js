import React, { Component } from 'react';
import { Card, CardColumns } from 'react-bootstrap'

class FilmList extends Component {
  render() {
    return (
      <CardColumns style={{display: 'flex', flexDirection: 'row', flexWrap: 'wrap'}}>
        {this.props.films.map(act => {
          return <Card className="text-center" style={{ width: '18rem', minWidth: '18rem', marginRight: '20px', flex: 0, marginTop: '50px' }} key={act.label.value}>
            <Card.Img variant="top" src="../téléchargement.svg" />
            <Card.Title>{act.label.value}</Card.Title>
          </Card>
        })}
      </CardColumns>
    );
  }
}

export default FilmList;
