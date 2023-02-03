import { Redirect, Route } from 'react-router-dom';
import { IonApp, IonButtons, IonHeader, IonMenuButton, IonRouterOutlet, IonTitle, IonToolbar, setupIonicReact } from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';


/* Core CSS required for Ionic components to work properly */
import '@ionic/react/css/core.css';

/* Basic CSS for apps built with Ionic */
import '@ionic/react/css/normalize.css';
import '@ionic/react/css/structure.css';
import '@ionic/react/css/typography.css';

/* Optional CSS utils that can be commented out */
import '@ionic/react/css/padding.css';
import '@ionic/react/css/float-elements.css';
import '@ionic/react/css/text-alignment.css';
import '@ionic/react/css/text-transformation.css';
import '@ionic/react/css/flex-utils.css';
import '@ionic/react/css/display.css';

/* Theme variables */
import './theme/variables.css';
import ListeEnchere from './vehicule/ListeEnchere';
import EnchereInsert from './enchere/EnchereInsert';
import DemandeRecharge from './demandeRecharge/DemandeRecharge';
import Menu from './component/IonMenu';
import Accueil from './component/Accueil';
import Login from './pages/Login';
import Signup from './pages/Signup';

setupIonicReact();
const App: React.FC = () => (
  <IonApp>
    <IonReactRouter>
      <IonRouterOutlet>
        <Route path="/" exact={true}>
          <Login/>
        </Route>
        <Route path="/Signup" exact={true}>
          <Signup/>
        </Route>
        <Route path="/Main" exact={true}>
          <Accueil/>
        </Route>
        <Route path="/listenchere" exact={true}>
          <ListeEnchere/>
        </Route>
        <Route path="/ins2" exact={true}>
          <EnchereInsert ></EnchereInsert>
        </Route>
        <Route path="/demandeRecharge" exact={true}>
          <DemandeRecharge idUtilisateur={localStorage.getItem("idUser")}></DemandeRecharge>
        </Route>
      </IonRouterOutlet>
    </IonReactRouter>
  </IonApp>
);

export default App;
export interface ErrInfo {
  code: number;
  message: string | null;
}
export interface Errors {
  info: ErrInfo;
}
