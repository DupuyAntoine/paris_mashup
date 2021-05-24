import React, { Component } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Home from './components/main/Home';
import About from './components/main/About';
import Arrondissement from './components/arrondissements/Arrondissement';
import Activity from './components/activities/Activity';
import RandomActivities from './components/main/RandomActivities';
import RandomFilms from './components/main/RandomFilms';
import { Navbar, Nav } from 'react-bootstrap'
import 'bootstrap/dist/css/bootstrap.css';

class App extends Component {
  render() {
    return(
      <div style={{
        background: 'linear-gradient(180deg, rgba(0,177,255,0.5) 0%, rgba(0,117,255,0.5) 50%, rgba(0,23,255,0.5) 100%)'
      }}>
        <div className="col-md-12">
          <Router>
            <Navbar variant="dark" style={{background: '#CE2B08'}} expand="lg" sticky="top">
              <Navbar.Brand href="/" style={{marginLeft: '20px'}}>Découvrez Paris !</Navbar.Brand>
              <Navbar.Toggle aria-controls="basic-navbar-nav" />
              <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="mr-auto">
                  <Nav.Link href="/">Accueil</Nav.Link>
                  <Nav.Link href="/films">Ca a été tourné dans le coin !</Nav.Link>
                  <Nav.Link href="/activities">Les activités à Paris</Nav.Link>
                  <Nav.Link href="/about">A propos de Paris</Nav.Link>
                </Nav>
              </Navbar.Collapse>
            </Navbar>
            <br />
            <Switch>
              <Route exact path='/' component={Home} />
              <Route path='/about' component={About} />
              <Route path='/films' component={RandomFilms} />
              <Route path='/activities' component={RandomActivities} />
              <Route path='/arrondissement/:name' component={Arrondissement} />
              <Route path='/activity/:name' component={Activity} />
            </Switch>
          </Router>
        </div>
      </div>
    )  
  }
}

export default App;
