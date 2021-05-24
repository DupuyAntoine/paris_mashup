import React, { Component } from 'react';
import { Link } from "react-router-dom";
import { Button, Card, CardColumns } from 'react-bootstrap'

class ActivityList extends Component {
  render() {
    return (
      <CardColumns style={{display: 'flex', flexWrap: 'wrap'}}>
        {this.props.activities.map(act => {
          return <Card className="text-center" style={{ width: '18rem', minWidth: '18rem', marginRight: '20px', flex: 0, marginTop: '50px', height: '100%', paddingBottom: '30px', color: 'black' }} key={act.label.value}>
            <Card.Img variant="top" src={act.url_image ? act.url_image.value : "../téléchargement.svg"} />
            <Card.Title>{act.label.value}</Card.Title>
            <Card.Text>{act.date ? new Date(act.date.value).toLocaleDateString("fr-FR") : ""}</Card.Text>
            <Link to={
              {
                  pathname: "/activity/" + act.uri.value.split('#')[1],
                  state: {
                      label: act.label.value,
                      uri: act.uri.value,
                      arrondissement : this.props.arrondissement
                  }
              }
          }>
            <Button variant="secondary">Plus d'infos</Button>
          </Link>
        </Card>})}
      </CardColumns>
    );
  }
}

export default ActivityList;
