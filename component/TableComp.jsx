import React,{Component} from "react";
export default class TableComp extends Component {
    state={
        header:[],
        data:[]
    };
    constructor(props){
        super(props);
        this.state.header=this.props.header;
        this.state.data=this.props.data;
    }

    render(){
        return (
            <div className="card shadow mb-3">
                                        
                <div className="title-card card-header py-3">
                    <p className="text m-0 fw-bold">{this.props.title}</p>
                </div>
        
                <div className="card-body">
                    <div className="d-flex flex-column flex-shrink-0">
                        <div className="table-responsive">
                            <table border={this.props.border} cellSpacing={this.props.cellSpacing} className={this.props.className}>
                                <thead>
                                    <tr>
                                        {
                                            this.props.header.map((i=> <th key={i} className="col">{i}</th>))
                                        }
                        
                                    </tr>
                                </thead>
                                <tbody>
                                    {this.props.data.map((d => 
                                        <tr>
                                            {
                                                this.props.header.map((j)=><td>{d[j]}</td>)
                                            }
             
                                        </tr>    
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>


                </div>
        
        </div>
        )
    }
}