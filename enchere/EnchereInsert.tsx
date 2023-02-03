import React, { Component, useEffect, useState } from "react";
import { interval } from "../data/type";
import IntervalInput from "./IntervalInput";
import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar, setupIonicReact } from "@ionic/react";
import { FormProvider, useForm } from "react-hook-form";
import PasswordInfo from "./PasswordInfo";
import { Swiper, SwiperSlide } from "swiper/react";
import BasicEnchere from "./BasicEnchere";
import LotInsert from "./LotInsert";
import Categories from "./Categories";
import FetchHelper from "../Helper/FetchHelper";
import URLHelper from "../Helper/URLHelper";
interface IState {
    debut?: number,
    time?: interval,
    price?: number,
    commission?: number,
    mySlides: any;
    formData: any;
}

const slideOpts = {
    initialSlide: 0,
    speed: 400,
};
setupIonicReact();
const Enchereinsert: React.FC = () => {
    const [mySlides, setMySlides] = useState<any>(null)
    const [formData, setFormData] = useState<any>();
    const [categories, setCategories] = useState([]);
    const [photo, SetPhotos] = useState<any>([]);
    const methods = useForm();
    const { handleSubmit, trigger } = methods;

    useEffect(() => {
        // mySlides?.lockSwipes(true);
    });

    const next = async (fields: any) => {
        const result = await trigger(fields);
        if (!result) return;
        // await mySlides?.lockSwipes(false);
        await mySlides?.slideNext();
        // await mySlides?.lockSwipes(true);
    };

    const prev = async () => {
        // await mySlides?.lockSwipes(false);
        await mySlides?.slidePrev();
        // await mySlides?.lockSwipes(true);
    };

    const onSubmit = async (data: any) => {
        console.log(data);
        categories.shift();
        console.log(categories);
        console.log(photo);
        let liste = []
        for (let i = 0; i < categories.length; i++) {
            let temp = {
                id: categories[i]
            }
            liste.push(temp);
        }
        let alefa = {
            enchere: {
                debut: data.debut,
                duree: {
                    years: (Number.isNaN(data.years)) ? 0 : data.years,
                    months: (Number.isNaN(data.months)) ? 0 : data.months,
                    days: (Number.isNaN(data.days)) ? 0 : data.days,
                    hours: (Number.isNaN(data.hours)) ? 0 : data.hours,
                    minutes: (Number.isNaN(data.minutes)) ? 0 : data.minutes,
                    wholeSeconds: (Number.isNaN(data.wholeSeconds)) ? 0 : data.wholeSeconds,
                    microSeconds: (Number.isNaN(data.microSeconds)) ? 0 : data.microSeconds
                },
                prixDeMisEnEnchere: data.prix,
                commission: data.commission,

            },
            lot: {
                nomLot: data.nom,
                descriptionLot: data.description,
                valeur: data.valeur,
                nombre: data.nombre,
                utilisateurId: localStorage.getItem("idUser")
            },
            liste: liste,
            photo64: photo
        }
        console.log(JSON.stringify(alefa));
        let authen = localStorage.getItem("token");
        let senddata = async (alefa: any) => {
            let valiny = await FetchHelper.getDataPOST(URLHelper.urlgen("encheres/insertenchere"), { 'authorization': authen, 'Content-type': "application/json; charset=UTF-8" }, alefa)
            // console.log(valiny);
            return valiny;
        }
        let res=await senddata(alefa);
        if("error" in res) {
            alert(res.error);
        }
        else{
            window.location.reload();
        }
        setFormData(data);
    };
    return (
        <>
            <IonPage>
                <IonHeader>
                    <IonToolbar>
                        <IonTitle>React Hook Form Wizard</IonTitle>
                    </IonToolbar>
                </IonHeader>
                <IonContent>
                    <FormProvider {...methods}>
                        <form onSubmit={handleSubmit(onSubmit)} autoComplete="off">
                            <Swiper
                                spaceBetween={100}
                                slidesPerView={1}
                                noSwipingClass="za"
                                noSwiping={true}
                                onSlideChange={() => console.log("slide change")}
                                onSwiper={(swiper) => setMySlides(swiper)}
                            >

                                <SwiperSlide className={"za"}>
                                    <BasicEnchere next={next} class={"noSwipingClass"}></BasicEnchere>
                                </SwiperSlide>
                                <SwiperSlide>
                                    <IntervalInput next={next} prev={prev} class={"noSwipingClass"}></IntervalInput>
                                </SwiperSlide>
                                <SwiperSlide>
                                    <LotInsert shoot={SetPhotos} prev={prev} next={next} class={"noSwipingClass"}></LotInsert>
                                </SwiperSlide>
                                <SwiperSlide>
                                    <Categories caller={setCategories} prev={prev} class={"noSwipingClass"}></Categories>
                                </SwiperSlide>
                            </Swiper>

                        </form>
                    </FormProvider>
                </IonContent>
            </IonPage>
        </>
    );
}
export default Enchereinsert;
// export default class Enchereinsert extends  Component<{}, IState>{
//     methods = useForm();
//
//     state:IState={
//         debut:0,
//         mySlides: null,
//         formData: null,
//         time:{
//             years:0,
//             days:0,
//             hours:0,
//             isNull:"false",
//             microSeconds:0,
//             minutes:0,
//             months:0,
//             wholeSeconds:0
//         },
//         price:0,
//         commission:0
//
//     }
//
//     next = async (fields: any) => {
//         const result = await this.methods.trigger(fields);
//         if (!result) return;
// // await this.state.mySlides?.lockSwipes(false);
//         await this.state.mySlides?.slideNext();
// // await this.state.mySlides?.lockSwipes(true);
//     };
//
//     prev = async () => {
// // await this.state.mySlides?.lockSwipes(false);
//         await this.state.mySlides?.slidePrev();
// // await this.state.mySlides?.lockSwipes(true);
//     };
//
//     onSubmit = (data: any) => {
//         console.log(data);
//         this.setState({ formData: data });
//     };
//     setTime=(t:interval)=>{
//         this.setState({time:t});
//         alert(t.months);
//     }
//     render(): React.ReactNode {
//         return (
//             <>
//                 {/*<IntervalInput call={this.setTime} ></IntervalInput>*/}
//                 <FormProvider {...this.methods}>
//                     <form onSubmit={this.methods.handleSubmit(this.onSubmit)} autoComplete="off">
//                         <Swiper
//                             spaceBetween={50}
//                             slidesPerView={1}
//                             noSwiping={true}
//                             noSwipingClass="noSwipingClass"
//                             onSlideChange={() => console.log('slide change')}
//                             onSwiper={(swiper) => this.setState({ mySlides: swiper })}
//                         >
//                             <SwiperSlide className="noSwipingClass">
//                                 <BasicInfo next={this.next} className="noSwiping" />
//                             </SwiperSlide>
//                             <SwiperSlide className="noSwipingClass">
//                                 <PasswordInfo next={this.next} prev={this.prev} />
//                             </SwiperSlide>
//                         </Swiper>
//                     </form>
//                 </FormProvider>
//             </>
//         )
//     }
// }