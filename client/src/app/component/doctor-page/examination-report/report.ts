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
export class Room{
    id:number;
    name: string;
    code:string;
}
export class Appointment{
    constructor(){
      //  this.pacientId = 1;
        this.begining = "00:00";
        this.ending= "24:00";
      //  this.room= 1;
    }
    room:number;
    pacientId: number;
    begining:string;
    ending:string;
}