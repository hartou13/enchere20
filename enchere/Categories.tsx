import React, {useEffect, useRef, useState} from "react";
import FetchHelper from "../Helper/FetchHelper";
import {getEnabledCategories} from "trace_events";
import {getListeCategories} from "../data/type";
import {IonButton, IonCheckbox, IonItem, IonLabel} from "@ionic/react";
import {useFormContext} from "react-hook-form";
import {call} from "ionicons/icons";

const  Categories:React.FC<any>=({prev,caller})=>{
    const {
        register,
        formState: { errors },
    } = useFormContext(); // retrieve all hook methods
    const [categories,setCategories]=useState([
        {
            refCategorie: "CT001",
            nomCategorie: "vin",
            id: 1
        }
    ]);
    const [selectedValues, setSelectedValues] = useState([{}]);
    useEffect(() => {
        const fetchData = async () => {
            const ls = await getListeCategories();
            setCategories(ls.data);
        };

        fetchData();
    }, []);

    const handleChange = (event:any) => {
        if (event.target.checked) {
            setSelectedValues([...selectedValues, event.target.value]);
            caller([...selectedValues,event.target.value]);
        } else {
            setSelectedValues(selectedValues.filter((value) => value !== event.target.value));
            caller(selectedValues.filter((value) => value !== event.target.value));
        }

    };
    return(
        <>
            <h1>Categorie (4/4) </h1>
            {
                categories.map(i=>
                    <IonItem>
                        <IonCheckbox slot="start" value={i.id}
                                     onIonChange={handleChange}
                          {...register("categorie",{})}
                                     />
                        <IonLabel>{i.nomCategorie}</IonLabel>
                    </IonItem>
                )
            }
            <IonButton onClick={() => prev()}>Prev</IonButton>
            <IonButton type="submit" color={"success"}>SUBMIT</IonButton>
        </>
    )
}
export  default  Categories;