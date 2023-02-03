
import FetchHelper from "../Helper/FetchHelper";
import URLHelper from "../Helper/URLHelper";
export  interface Enchere{
    id:number,
    refEnchere:string,
    debut:string,
    duree:interval,
    prixDeMisEnEnchere:number,
    idLot:number,
    Commission:number,
    maxmise:number,
    idUtilisateur:number
};
export interface interval{
    years?: number,
                    months?: number,
                    days?: number,
                    hours?: number,
                    minutes?: number,
                    wholeSeconds?: number,
                    microSeconds?: number,
                    isNull?: string,
}

export const getListeCategories = async () => {
    let authen = localStorage.getItem("token");
    let retour = await (FetchHelper.getData(URLHelper.urlgen("categorie/getAll"), { 'authorization': authen }));
    console.log(retour);

    return retour;
}