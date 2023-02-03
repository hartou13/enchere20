import FetchHelper from "../Helper/FetchHelper";
import URLHelper from "../Helper/URLHelper";

export interface EnchereLotStatut{
    id:number;
    refEnchere: string;
    debut: string;
    duree: Duree;
    prixDeMisEnEnchere: number;
    idLot: number;
    Commission: number;
    status: string;
    reflot: string;
    nomlot: string;
    descriptionlot: string;
    valeur: number;
    nombre: number;
    utilisateur: number;
}
export interface Duree{

    years: number;
    months:number;
    days: number;
    hours: number;
    minutes:number;
    wholeSeconds:number;
    microSeconds:number;
}

let data: EnchereLotStatut[]=[
    {
        reflot: "LO001",
        nomlot: "cageot vin",
        descriptionlot: "20 bouteilles",
        status: "going on",
        valeur: 200000.0,
        nombre: 1.0,
        utilisateur: 1,
        refEnchere: "EN001",
        debut: "Jan 10, 2023, 12:00:00 AM",
        duree: {
            years: 0,
            months: 0,
            days: 20,
            hours: 2,
            minutes: 3,
            wholeSeconds: 4,
            microSeconds: 0
        },
        prixDeMisEnEnchere: 20000.0,
        idLot: 1,
        Commission: 5.0,
        id: 1
    },
    {
        reflot: "LO002",
        nomlot: "Ford Fiesta",
        descriptionlot: "Bon ‚tat",
        status: "going on",
        valeur: 2000000.0,
        nombre: 1.0,
        utilisateur: 1,
        refEnchere: "EN002",
        debut: "Jan 10, 2023, 12:00:00 AM",
        duree: {
            years: 0,
            months: 0,
            days: 20,
            hours: 0,
            minutes: 0,
            wholeSeconds: 0,
            microSeconds: 0,
        },
        prixDeMisEnEnchere: 500000.0,
        idLot: 2,
        Commission: 5.0,
        id: 2
    },
    
]
export const formatDuree=(duree: Duree)=>{
    let res:string="";
    if(duree.days>0){
        res+=duree.days+" days ";
    }
    if(duree.months>0){
        res+=duree.months+" months ";
    }
    if(duree.years>0){
        res+=duree.years+" years ";
    }
    if(duree.hours>0){
        res+=duree.hours+" hours ";
    }
    if(duree.minutes>0){
        res+=duree.minutes+" minutes ";
    }
    if(duree.wholeSeconds>0){
        res+=duree.wholeSeconds+" seconds ";
    }
    return res
}
type Promise = {
    data: EnchereLotStatut[]
}
export const fetchEnchere= async ( ) =>{
    let data = await FetchHelper.getData(URLHelper.urlgen("encheres/ownAuction/1"));
    console.log(data);
    return data.data;
}

