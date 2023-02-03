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
import {interval} from '../data/type'
class IntervalBadge extends Component<{interval: interval,color:string}, {}> {
    render() {
        const { years, months, days, hours, minutes, wholeSeconds, microSeconds } = this.props.interval;
        return (
            <IonBadge color={this.props.color}>
                {years !== 0 && <span color="secondary">{years} Years</span>}
                {months !== 0 && <span color="secondary">{months} Months</span>}
                {days !== 0 && <span color="secondary">{days} Days</span>}
                {hours !== 0 && <span color="secondary">{hours} Hours</span>}
                {minutes !== 0 && <span color="secondary">{minutes} Minutes</span>}
                {wholeSeconds !== 0 && <span color="secondary">{wholeSeconds} Seconds</span>}
                {microSeconds !== 0 && <span color="secondary">{microSeconds} Microseconds</span>}
            </IonBadge>
        );
    }
}

export default IntervalBadge;