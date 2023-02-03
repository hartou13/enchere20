import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/react';
import React, { useState } from 'react';
import { IonGrid, IonRow, IonCol } from '@ionic/react';
import { personCircle } from "ionicons/icons";
import { IonItem, IonLabel, IonInput, IonButton, IonIcon, IonAlert } from '@ionic/react';
import   URLHelper  from '../Helper/URLHelper';
import   FetchHelper  from '../Helper/FetchHelper';
import { IonDatetime } from '@ionic/react';


const Signup: React.FC = () => {
  const [email, setEmail] = useState<string>("testexample@gmail.com");
  const [password, setPassword] = useState<string>("test ");
  const [nom, setNom] = useState<string>("testNom");
  const [prenom, setPrenom] = useState<string>("testPrenom");
  const [dateDeNaissance, setDateDeNaissance] = useState<string>("2000-01-01");
  const [message, setMessage] = useState<string>("");
  const [iserror, setIserror] = useState<boolean>(false);
  const infoUser = {
    "email": email,
    "mdp": password,
    "nom":nom,
    "prenom":prenom,
    "dateDeNaissance":dateDeNaissance
  }
  const handleLogin = async (event:any)=>{
    event.preventDefault();
    console.log(infoUser);
    let response=await FetchHelper.getDataPost(URLHelper.urlgen("Inscription"), infoUser);
    console.log(response);
    if("info" in response){
        setMessage( response.info.message )
        setIserror(true);
    }
    else{
        window.location.replace("/Main");
    }

}

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Login</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen className="ion-padding ion-text-center">
        <IonGrid>
        <IonRow>
          <IonCol>
            <IonIcon
                style={{ fontSize: "70px", color: "#0040ff" }}
                icon={personCircle}
            />
          </IonCol>
        </IonRow>
        <IonAlert
                isOpen={iserror}
                onDidDismiss={() => setIserror(false)}
                cssClass="my-custom-class"
                header={"Error!"}
                message={message}
                buttons={["Dismiss"]}
        />

        <form id="login">

          <IonRow>
            <IonCol>
            <IonItem>
            <IonLabel position="floating"> Email</IonLabel>
            <IonInput
                type="email"
                value={email}
                onIonChange={(e) => setEmail(e.detail.value!)}
                >
            </IonInput>
            </IonItem>
            </IonCol>
          </IonRow>

          <IonRow>
            <IonCol>
            <IonItem>
              <IonLabel position="floating"> Password</IonLabel>
              <IonInput
                type="password"
                value={password}
                onIonChange={(e) => setPassword(e.detail.value!)}
                >
              </IonInput>
            </IonItem>
            </IonCol>
          </IonRow>

          <IonRow>
            <IonCol>
            <IonItem>
              <IonLabel position="floating"> Nom</IonLabel>
              <IonInput
                type="text"
                value={nom}
                onIonChange={(e) => setNom(e.detail.value!)}
                >
              </IonInput>
            </IonItem>
            </IonCol>
          </IonRow>

          <IonRow>
            <IonCol>
            <IonItem>
              <IonLabel position="floating"> Prenom</IonLabel>
              <IonInput
                type="text"
                value={prenom}
                onIonChange={(e) => setPrenom(e.detail.value!)}
                >
              </IonInput>
            </IonItem>
            </IonCol>
          </IonRow>

          <IonRow>
            <IonCol>
            <IonItem>
              <IonLabel position="floating">Date de Naissance</IonLabel>
              <IonInput type="datetime-local" value={dateDeNaissance} 
              onIonChange={(e) => setDateDeNaissance(e.detail.value!)}>
              </IonInput>
            </IonItem>
            </IonCol>
          </IonRow>

          <IonRow>
            <IonCol>
              <IonButton expand="block" onClick={handleLogin}>Login</IonButton>
              <p style={{ fontSize: "medium" }}>
                  already have an account? <a href="/Login">Sign in</a>
              </p>

            </IonCol>
          </IonRow>
        </form>

        </IonGrid>
      </IonContent>
    </IonPage>
  );
};

export default Signup;

