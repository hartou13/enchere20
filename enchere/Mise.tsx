import { useState } from 'react';
import { Message, getMessage } from '../data/messages';
import {
  IonBackButton,
  IonButtons,
  IonContent,
  IonHeader,
  IonPage,
  IonToolbar,
  useIonViewWillEnter,
} from '@ionic/react';
import { useParams } from 'react-router';

function Mise() {
  const [id, setMessage] = useState<number>();
  const params = useParams<{ id: string }>();

  useIonViewWillEnter(() => {
    const id = parseInt(params.id, 10);
    setMessage(id);
  });

  return (
    <IonPage id="view-message-page">
      <IonHeader translucent>
        <IonToolbar>
          <IonButtons slot="start">
            <IonBackButton text="Inbox" defaultHref="/home"></IonBackButton>
          </IonButtons>
        </IonToolbar>
      </IonHeader>
        
      <IonContent fullscreen>
        <p>{id}</p>
      </IonContent>
    </IonPage>
  );
}

export default Mise;
