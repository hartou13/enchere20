import React,{Component} from "react";
import InputComp from "../component/InputComp";
import {interval} from "../data/type";
import {InputChangeEventDetail, IonButton, IonInput, IonItem, IonLabel} from "@ionic/react";
import {useFormContext} from "react-hook-form";
const BasicEnchere:React.FC<any>=({next})=>{
    const {
        register,
        formState: { errors },
    } = useFormContext(); // retrieve all hook methods
    let liste=["debut","prix","commission"];
    return(
        <>
            <h1> A Propos de l'enchere 1/4</h1>
                    {
                        liste.map(i=>
                        <IonItem>
                            <IonLabel position={"floating"}>{i}</IonLabel>
                            <IonInput
                            type={(i==="debut")?"datetime-local":"number"}
                            {...register(i,{})}
                            >

                            </IonInput>
                        </IonItem>
                        )
                    }
                <div className="form-footer">
                    <IonButton onClick={() => next(liste)}>Next</IonButton>
                </div>
        </>
    )
}
export  default BasicEnchere;