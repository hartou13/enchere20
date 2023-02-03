import { IonBackButton, IonButton, IonButtons, IonCard, IonCardContent, IonContent, IonHeader, IonInput, IonLabel, IonToolbar } from "@ionic/react";
import React from "react";
import FetchHelper from "../Helper/FetchHelper";
import URLHelper from "../Helper/URLHelper";
var sold="0";
type Props={
    idUtilisateur: string|null
};
export default class DemandeRecharge extends React.Component<Props>{

    

    handleSubmit=()=>{
        this.sendData();
    }

    sendData= async ()=>{
        var url="dm/demande/"+this.props.idUtilisateur+"?sold="+sold;
        let response=await FetchHelper.getData(URLHelper.urlgen(url));
        console.log(response);
        if("error" in response){
            console.log(({ erreur: response.error.message}))
        }
        else{

        }
    }

    render() {
        return(
            <IonHeader translucent>
                
                <IonContent fullscreen>
                <IonCard>
                <IonCardContent>
                    <IonLabel>
                        <p>Demande de recharge</p> 
                    </IonLabel>
                    <br />
                    <IonLabel>
                        <div>
                        <IonLabel position='floating'>Sold :</IonLabel>
                        <IonInput type='number' placeholder="Solde a demander" onIonChange={(e) => sold=e.detail.value!}/>
                        <br />
                        <IonButton onClick={this.handleSubmit}>Envoyer</IonButton>
                        </div>
                    </IonLabel>
                </IonCardContent>
                </IonCard>
                </IonContent>
            </IonHeader>
        )
    }
}