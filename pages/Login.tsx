import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/react';
import React, { useState } from 'react';
import { IonGrid, IonRow, IonCol } from '@ionic/react';
import { personCircle } from "ionicons/icons";
import { IonItem, IonLabel, IonInput, IonButton, IonIcon, IonAlert } from '@ionic/react';
import   URLHelper  from '../Helper/URLHelper';
import   FetchHelper  from '../Helper/FetchHelper';


const Login: React.FC = () => {
  const [email, setEmail] = useState<string>("test@example.com");
  const [password, setPassword] = useState<string>("test");
  const [message, setMessage] = useState<string>("");
  const [iserror, setIserror] = useState<boolean>(false);
  const loginData = {
    "email": email,
    "mdp": password
  }
  const handleLogin = async (event:any)=>{
    event.preventDefault();
    console.log(loginData);
    let response=await FetchHelper.getDataPost(URLHelper.urlgen("ConnexionUser"), loginData);
    console.log(response);
    if("info" in response){
        setMessage( response.info.message )
        setIserror(true);
    }
    else{
        localStorage.setItem("token", response.data.token);
        localStorage.setItem("idUser", response.data.idadmin);
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
                onIonChange={(e) => e.detail.value!}
                >
              </IonInput>
            </IonItem>
            </IonCol>
          </IonRow>

          <IonRow>
            <IonCol>
              <IonButton expand="block" onClick={handleLogin}>Login</IonButton>
              <p style={{ fontSize: "medium" }}>
                  Don't have an account? <a href="/Signup">Sign up!</a>
              </p>

            </IonCol>
          </IonRow>
        </form>

        </IonGrid>
      </IonContent>
    </IonPage>
  );
};

export default Login;
