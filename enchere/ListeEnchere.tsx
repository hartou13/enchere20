import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/react'
import React, { Component } from 'react'
import { Enchere } from '../data/type'
import EnchereGoing from './EnchereGoing'
import {fetchOnGoing} from './data'

interface IState {
  liste: Enchere[]
}
export default class ListeEnchere extends Component<any, IState> {
  state:IState = {
    liste: [{
      id: 1,
      refEnchere: 'ENCHERE00192',
      debut: '2023-01-09',
      duree: {
        minutes:5
      },
      prixDeMisEnEnchere: 35000,
      idLot: 1,
      Commission: 30,
      maxmise: 1000000,
      idUtilisateur: 1,
    },
    {
        id: 2,
        refEnchere: 'ENCHERE00192',
        debut: '2023-01-09',
        duree: {
          minutes:5
        },
        prixDeMisEnEnchere: 35000,
        idLot: 1,
        Commission: 30,
        maxmise: 1000000,
        idUtilisateur: 1,}
],
  }
  async componentDidMount(): Promise<void> {
    let retour = await fetchOnGoing();
    // console.log(retour);
    if (retour.info !== undefined) {
      alert(retour.info);
    } else {
      let data= retour.data;
      console.log(data);
      this.setState({ liste: data.liste });
    }
  }

  render(): React.ReactNode {
    console.log("data");
    console.log(this.state.liste);
    return (
      <IonPage id="home-page">
        <IonHeader>
          <IonToolbar>
            <IonTitle>Liste Enchere</IonTitle>
          </IonToolbar>
        </IonHeader>
        <IonContent fullscreen>
            {this.state.liste.map((e)=> <EnchereGoing enchere={e} devise={"Ar"}></EnchereGoing>)}
           
        </IonContent>
      </IonPage>
    )
  }
}
