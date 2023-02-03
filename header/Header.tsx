import * as React from 'react';
import { Component } from 'react';
import { IonHeader, IonToolbar, IonTitle, IonButtons, IonMenuButton, IonRouterLink, IonButton, IonTabs, IonRouterOutlet, IonIcon, IonLabel, IonTabBar, IonTabButton, IonPage } from '@ionic/react';
import { home, chatbubbles, person } from 'ionicons/icons';
import ListeEnchere from '../enchere/ListeEnchere';
import EnchereInsert from '../enchere/EnchereInsert';
import DemandeRecharge from '../demandeRecharge/DemandeRecharge';
interface HeaderProps {
    
}
 
interface HeaderState {
    
}
 
class Header extends React.Component<HeaderProps, HeaderState> {
    state = {   }
    render() { 
        return (  
          <IonPage id="main-content">
          <IonHeader>
            <IonToolbar>
              <IonButtons slot="start">
                <IonMenuButton></IonMenuButton>
              </IonButtons>
              <IonTitle>Menu</IonTitle>
            </IonToolbar>
          </IonHeader>
          
        </IonPage>

        );
    }
}
 
export default Header;