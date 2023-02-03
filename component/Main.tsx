import { IonButton, IonContent, IonPage } from '@ionic/react';
import * as React from 'react';
import { Component } from 'react';
interface MainProps {
    
}
 
interface MainState {
    
}
class Main extends React.Component<MainProps, MainState> {
    state = { }
    decon=()=>{
        localStorage.removeItem("idUser");
        localStorage.removeItem("token");
    }
    
    render() { 
        return ( 
            <IonPage>
                <IonContent className="ion-padding">
                    <IonButton routerLink='/listenchere'>Liste Enchere</IonButton>
                    <IonButton routerLink='/ins2'>creation Enchere</IonButton>
                    <IonButton routerLink='/demandeRecharge'>Demande recharge</IonButton>
                    <IonButton routerLink='/' onClick={this.decon} >deconnexion</IonButton>
                </IonContent>
            </IonPage>

         );
    }
}
 
export default Main;