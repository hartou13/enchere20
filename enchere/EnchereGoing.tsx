import {
  IonBackButton,
  IonBadge,
  IonButtons,
  IonContent,
  IonHeader,
  IonItem,
  IonLabel,
  IonNote,
  IonPage,
  IonToolbar,
} from '@ionic/react'
import React, { Component } from 'react'
import { Enchere } from '../data/type'
import './onGoing.css'
import IntervalBadge from './IntervalBadge'
interface EnchereProps {
  enchere: Enchere
  devise: string
}
export default class EnchereGoing extends Component<EnchereProps, any> {
  render(): React.ReactNode {
    return (
      <IonItem routerLink={`/mise/${this.props.enchere.id}`} detail={false}>
        <IonLabel class="ion-text-wrap">
          <h2 className="text w-100">
            {this.props.enchere.refEnchere}
            <span className="date">
                <IntervalBadge interval={this.props.enchere.duree} color="warning"></IntervalBadge>
            </span>
          </h2>
          <h3>
            Mise Max:<IonBadge color="success">{this.props.enchere.prixDeMisEnEnchere} {this.props.devise}</IonBadge>
            
            
          </h3>
        </IonLabel>
      </IonItem>
    )
  }
}
