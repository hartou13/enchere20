import React,{Component} from "react";
export default class InputComponent extends Component {
    state={
        value:""
    }

    handleChange=(event) => {
        const value=event.currentTarget.value;
        this.setState({value:value});
    }

    render(){
        return(
            <input name={this.props.name} value={this.state.value}
             id={this.props.id}
             className={this.props.className}
             type={this.props.type}
             onChange={this.handleChange}
             placeholder={this.props.placeholder}
             />
        )
    }
}