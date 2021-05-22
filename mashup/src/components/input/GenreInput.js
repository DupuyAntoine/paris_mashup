import React, { Component } from 'react';

class InputForm extends Component {
    state = {
      input: ''
    };
  
    // input change handler
    onInput = (e) => this.setState({
      input: e.target.value
    });
  
    // submit handler
    onSubmit = (e) => {
      e.preventDefault();
      this.props.onSubmit(this.state.input);
    }
  
    render (){
      return (
        <form onSubmit={this.onSubmit}>
          <input
            // use value and onChange so it will be a controlled component
            value={this.state.value}
            onChange={this.onInput}
            type="text"
            placeholder="Entrez votre genre préféré de film" />
          <button type="submit">Classement des arrondissements</button>
        </form>
      )
    }
  }
  
  export default InputForm;
