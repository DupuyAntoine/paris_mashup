import React, { Component } from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import Home from './components/main/Home';
import About from './components/main/About';
import Arrondissement from './components/arrondissements/Arrondissement';
import Activity from './components/activities/Activity';
import RandomActivities from './components/main/RandomActivities';
import RandomFilms from './components/main/RandomFilms';

class App extends Component {
  render() {
    return (
    <Router>
        <div>
          <h1>Découvrez Paris !</h1>
          <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <ul className="navbar-nav mr-auto">
            <li><Link to={'/'} className="nav-link">Accueil</Link></li>
            <li><Link to={'/films'} className="nav-link">Les films tournés à Paris</Link></li>
            <li><Link to={'/activities'} className="nav-link">Les activités à Paris</Link></li>
            <li><Link to={'/about'} className="nav-link">A propos de Paris</Link></li>
          </ul>
          </nav>
          <hr />
          <Switch>
              <Route exact path='/' component={Home} />
              <Route path='/about' component={About} />
              <Route path='/films' component={RandomFilms} />
              <Route path='/activities' component={RandomActivities} />
              <Route path='/arrondissement/:name' component={Arrondissement} />
              <Route path='/activity/:name' component={Activity} />
          </Switch>
        </div>
      </Router>
    );
  }
}

export default App;
