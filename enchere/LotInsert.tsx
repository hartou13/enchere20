import {useFormContext} from "react-hook-form";
import {IonButton, IonIcon, IonInput, IonItem, IonLabel, IonTextarea} from "@ionic/react";
import React, {useState} from "react";
import { sync } from "ionicons/icons";

 interface Photo{
    photos:string|ArrayBuffer|null;
}
const LotInsert:React.FC<any>=({next,prev,shoot})=>{
    const {
        register,
        formState: { errors },
    } = useFormContext(); // retrieve all hook methods

    const [listesary,setSary]=useState<any>([1]);
    const [saries,setSaries]=useState<any>([]);
    const [isa,setIsa]=useState<number>(1);
    let liste=["nom","description","valeur","nombre"];
    const ajouterPhoto=()=>{
        let temp=[...listesary,1];
        setSary(temp);
        console.log(listesary.length);
    }
    const finish=()=>{
        console.log(saries.length);
        shoot(saries);
    }
    const effacer=()=>{
        let temp=[...listesary];
        temp.pop();
        setSary(temp);
    }
    let handleChange = (event: any) => {
        console.log(event.target.value);
        let file = event.target.files[0];
        console.log(file);
        let Reader = new FileReader();
        Reader.readAsDataURL(file);
        Reader.onload = () => {
            // console.log(Reader.result);
            let alefa: Photo = {
                photos: Reader.result,
            };
            // shoot(Reader.result);
            let temp=[...saries]
            temp.push(Reader.result);
            shoot(temp);
            setSaries(temp);
            // let retour = alefas(alefa);
            // console.log(retour);
        };
        Reader.onerror = () => {
            console.log(Reader.error);
        };
    };
    return(
        <>
            <h1>Lot Inser(3/4)</h1>
            <IonItem>
                <IonLabel position={"floating"}>Nom</IonLabel>
                <IonInput
                    type={"text"}
                    {...register("nom",{})}
                >

                </IonInput>
            </IonItem>
            <IonItem>
                <label className="label">
                    Photo
                    {
                        listesary.map((i:any,index:number)=>
                            <>
                                <IonItem>
                                <input  type="file" required onChange={handleChange} />
                                <span> <IonIcon size="small" icon={sync} color="secondary"></IonIcon>Upload Photo</span>
                                </IonItem>
                                </>
                        )
                    }

                </label>
                <IonButton onClick={ajouterPhoto}>Add</IonButton>
                <IonButton onClick={effacer} color={"danger"}>Delete</IonButton>
                {/*<IonButton onClick={finish} color={"success"}>Finish</IonButton>*/}
            </IonItem>

            <IonItem>
                <IonLabel position={"floating"}>Description</IonLabel>
                <IonTextarea
                    placeholder="Enter text, leave the textarea, come back, and type to clear"
                    clearOnEdit={true}
                    {...register("description",{})}
                ></IonTextarea>
            </IonItem>
            <IonItem>
                <IonLabel position={"floating"}>Valeur</IonLabel>
                <IonInput
                    type={"number"}
                    {...register("valeur",{})}
                >

                </IonInput>
            </IonItem>
            <IonItem>
                <IonLabel position={"floating"}>Nombre</IonLabel>
                <IonInput
                    type={"number"}
                    {...register("nombre",{})}
                >

                </IonInput>
            </IonItem>


            <IonButton onClick={() => prev()}>Prev</IonButton>
            <IonButton onClick={() => next(liste)}>Next</IonButton>
        </>
    )
}
export default  LotInsert