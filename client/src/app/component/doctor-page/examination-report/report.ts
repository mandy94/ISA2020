export class Report{
    constructor(){
        this.medication= new Array<number>();
    }
    // got_meds_report:Array<Medication>;
    // diagnose_id: number;
    // user_jmbg: number;
    medication:any;
    pacientid: number;
    doctorid:number;
    details: string;

}
export class Medication{
    med_id: number;
    dosage: string;
    name: string;
    
}