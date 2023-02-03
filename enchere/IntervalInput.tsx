import React,{Component} from "react";
import {InputChangeEventDetail, IonButton, IonInput, IonItem, IonLabel} from "@ionic/react";
import {useFormContext} from "react-hook-form";
const IntervalInput:React.FC<any>=({next,prev})=>{
    const { register, formState: { errors }, } = useFormContext(); // retrieve all hook methods
    let liste=["years","months","days","hours","minutes","wholeSeconds","microSeconds"]
    return(
        <>
            {liste.map( i=>
                <IonItem >
                <IonLabel position="floating">{i}</IonLabel>
                <IonInput
                    type="number"
                    {...register(i,{valueAsNumber:true})}
                />
            </IonItem> ) }
            <div className="form-footer">
                <IonButton onClick={() => prev()} color={"primary"}>Prev</IonButton>
                <IonButton onClick={() => next(liste)} color={"primary"}>Next</IonButton>
            </div>

        </>
    )
}
export default IntervalInput
