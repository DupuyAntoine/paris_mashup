import React, { Component } from 'react';
import { Form, Button } from 'react-bootstrap'

class GenreInput extends Component {
    state = {
      select: '',
      genres: []
    };
  
    // submit handler
    onSubmit = (e) => {
      e.preventDefault();
      this.props.onSubmit(this.state.select);
    }

    onChange = (e) => {
      this.setState({select: e.target.value})
    }

    getGenre = () => {
      const encodedQuery = encodeURIComponent(`PREFIX : <http://www.semanticweb.org/colettedupuy/ontologies/2021/film_paris#>
      PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
      
      SELECT DISTINCT ?uri WHERE {
        ?film :est_classe_comme ?uri .
      }
      `);
      const apiUrl = `http://localhost:3030/FilmParis/query?query=${encodedQuery}`;
      fetch(apiUrl)
        .then((response) => response.json())
        .then((data) => {
          this.setState({genres: data.results.bindings})
          return true
        })
    }

    componentDidMount() {
      this.getGenre()
    }  

    render () {
      return (
        <Form onSubmit={this.onSubmit} role="form">
          <Form.Group>
            <Form.Label>Séléctionner votre genre favori :</Form.Label>
          </Form.Group>
          <Form.Group>
            <Form.Control as="select" onChange={this.onChange}>
              <option key="default" value="Choisissez un genre">Choisissez un genre</option>
              {this.state.genres.map(genre => {
                return <option 
                  key={genre.uri.value}
                  value={genre.uri.value.toLowerCase().split("#")[1]}
                >
                  {genre.uri.value.toLowerCase().split("#")[1].charAt(0).toUpperCase() + genre.uri.value.toLowerCase().split("#")[1].slice(1)}
                </option>
                })
              }
            </Form.Control>
            <br />
            <Button variant="secondary" type="submit">C'est parti !</Button>
          </Form.Group>
        </Form>
      )
    }
  }
  
  export default GenreInput;
