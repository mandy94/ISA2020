import { Therapy } from 'app/codebook';

export class Report{
    constructor(){
        this.medication= new Array<Medicine>();
        this.therapies = new Array<number>();
        
    }
    pacientid: number;
    doctorid:number;
    diagnose: Diagnose;
    therapies: Array<number>;
    medication: Array<Medicine>;
    details: string;

}
export class Diagnose{
    id:number;
    code:string;
    name:string;
}
export class Medicine{
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