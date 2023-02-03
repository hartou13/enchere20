import { IonMenu, IonHeader, IonToolbar, IonTitle, IonContent, IonButton, IonButtons, IonMenuButton, IonPage, IonIcon, IonItem, IonLabel, IonList, IonMenuToggle } from "@ionic/react";
import { book, analytics, informationCircle } from "ionicons/icons";
import React from "react";
import Header from "../header/Header";
import URLHelper from "../Helper/URLHelper";
import ListeEnchere from "../vehicule/ListeEnchere";
type Props2={
    page?:string
}
export default class Menu extends React.Component<Props2, any> {
    render(){
        return(
          <>
           <IonMenu contentId="main-content">
        <IonHeader>
          <IonToolbar>
            <IonTitle>Menu Content</IonTitle>
          </IonToolbar>
        </IonHeader>
        <IonContent className="ion-padding">
          <IonMenuToggle>
            <IonButton>Click to close the menu</IonButton>
          </IonMenuToggle>  
        </IonContent>
      </IonMenu>
      <IonPage id="main-content">
        <IonHeader>
          <IonToolbar>
            <IonTitle>Menu</IonTitle>
          </IonToolbar>
        </IonHeader>
        <IonContent className="ion-padding">
          <IonMenuToggle>
            <IonButton>Click to open the menu</IonButton>
          </IonMenuToggle>
        </IonContent>
        <ListeEnchere></ListeEnchere>
      </IonPage>
        </>
        )
    }
}