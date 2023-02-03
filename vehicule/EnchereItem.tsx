import React from 'react';
import { IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle } from '@ionic/react';

import { EnchereLotStatut, formatDuree } from '../data/enchere_lot_statut';
type Props = {
    enchere: EnchereLotStatut
};
type State = {
    en: EnchereLotStatut
}
export default class EnchereItem extends React.Component<Props, State>{
    state: State = {
        en: {
            reflot: "LO002",
            nomlot: "Ford Fiesta",
            descriptionlot: "Bon ‚tat",
            status: "going on",
            valeur: 2000000.0,
            nombre: 1.0,
            utilisateur: 1,
            refEnchere: "EN002",
            debut: "Jan 10, 2023, 12:00:00 AM",
            duree: {
                years: 0,
                months: 0,
                days: 20,
                hours: 0,
                minutes: 0,
                wholeSeconds: 0,
                microSeconds: 0,
            },
            prixDeMisEnEnchere: 500000.0,
            idLot: 2,
            Commission: 5.0,
            id: 2
        }
    }
    componentDidMount(): void {
        console.log(this.props.enchere);
        this.setState({ en: this.props.enchere });

    }
    public render() {
        return (
            <IonCard >
                <IonCardHeader>
                    <IonCardTitle>{this.state.en.nomlot} {this.state.en.id}</IonCardTitle>
                    <IonCardSubtitle>{this.state.en.status}</IonCardSubtitle>
                </IonCardHeader>
                <IonCardContent>
                    <p>{this.state.en.descriptionlot}</p> <p>nombre: {this.state.en.nombre}</p> 
                    <p>{this.state.en.debut}</p> <p>duree : {formatDuree(this.state.en.duree)}</p>
                </IonCardContent>
            </IonCard>
        )
    }
}
