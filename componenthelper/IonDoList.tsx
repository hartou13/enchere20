import React, { Component } from 'react';
import { IonItem, IonLabel } from '@ionic/react';

interface Props<T> {
  data: T[];
  headers: (keyof T)[];
}

class IonDoList<T> extends Component<Props<T>> {
  render() {
    const { data, headers } = this.props;

    return (
      <>
        {data.map((item, index) => (
          <IonItem key={index}>
            {headers.map((header, i) => {
                const value=item[header];
                return (
              <IonLabel className="ion-text-wrap" key={i}>
                <h2>{typeof header === "string"?header:<span>{i}</span>}</h2>
                <h3>{typeof value === "string" || typeof value === "number" || typeof value === "boolean"?value:<span>{i}</span>}</h3>
              </IonLabel>
                )})
            }
          </IonItem>
        ))}
      </>
    );
  }
}

export default IonDoList;
