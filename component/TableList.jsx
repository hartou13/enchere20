import './../assets/dist/css/bootstrap.min.css'
import './../assets/css/TableList.css'
import { useState } from 'react';
export default function TableList(props) {

    

    const tr =useState([
        {valeur1: "aa",valeur2:"bb",valeur3:"55"},
        {valeur1: "aa",valeur2:"bb",valeur3:"55"}
    ]); 


    return(
        <div className="card shadow mb-3">
                                        
                <div className="title-card card-header py-3">
                    <p className="text m-0 fw-bold">{props.title}</p>
                </div>
        
                <div className="card-body">
                    <div className="d-flex flex-column flex-shrink-0">
                        <div className="table-responsive">
                            <table className="table table-striped table-sm">
                                <thead>
                                    <tr>
                                    <th scope="col">Nom</th>
                                    <th scope="col">Prenom</th>
                                    <th scope="col">{props.list}</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {tr.map((td => 
                                        <tr>
                                            <td>{td.valeur1}</td>
                                            <td>{td.valeur2}</td>
                                            <td>{td.valeur3}</td>
                                        </tr>    
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>


                </div>
        
        </div>
    );
}