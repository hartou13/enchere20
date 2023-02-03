import { IonMenu, IonHeader, IonToolbar, IonTitle, IonContent, IonMenuToggle, IonButton, IonPage, IonButtons, IonMenuButton } from '@ionic/react';
import * as React from 'react';
import { Component } from 'react';
import DemandeRecharge from '../demandeRecharge/DemandeRecharge';
import EnchereInsert from '../enchere/EnchereInsert';
import ListeEnchere from '../vehicule/ListeEnchere';
interface AccueilProps {
    
}
 
interface AccueilState {
    
}
 
class Accueil extends React.Component<AccueilProps, AccueilState> {
    state = {
        element:<ListeEnchere></ListeEnchere>
     }
     constructor(props:AccueilProps){
         super(props);
         this.setState({element:<ListeEnchere/>})
     }
     element=()=>{
        return this.state.element;
     }
     listEnc=()=>{
        this.setState({element:<ListeEnchere/>})
     }
     createEnchere=()=>{
        this.setState({element:<EnchereInsert/>})
     }
     dem=()=>{
        this.setState({element:<DemandeRecharge idUtilisateur={localStorage.getItem("idUser")}></DemandeRecharge>})
     }
     decon=()=>{
      localStorage.removeItem("idUser");
      localStorage.removeItem("token");
  }
    render() { 
        return (  <>
            <IonMenu contentId="main-content">
              <IonHeader>
                <IonToolbar>
                  <IonTitle>Menu Content</IonTitle>
                </IonToolbar>
              </IonHeader>
              <IonContent className="ion-padding">
                <IonButton onClick={this.listEnc}>Liste Enchere</IonButton>
                <IonButton onClick={this.createEnchere}>creation Enchere</IonButton>
                <IonButton onClick={this.dem}>Demande recharge</IonButton>
                <IonButton routerLink='/' onClick={this.decon}>deconnexion</IonButton>
              </IonContent>
            </IonMenu>
            <IonPage id="main-content">
              <IonHeader>
                <IonToolbar>
                  <IonButtons slot="start">
                    <IonMenuButton></IonMenuButton>
                  </IonButtons>
                  <IonTitle>Menu</IonTitle>
                </IonToolbar>
              </IonHeader>
              <IonContent className="ion-padding">
                <div>
                    {this.state.element}
                </div>
                </IonContent>
            </IonPage>
          </>);
    }
}
 
export default Accueil;