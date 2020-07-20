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
export class MailMessage{
    constructor(r,t,c){
        // this.sender = s;
        this.receiver = r;
        this.title = t;
        this.content = c;
    }
    // sender: number;
    receiver:number;
    content:string;
    title:string;
}
export class Room{
    id:number;
    name: string;
    code:string;
}

export class Appointment{
    constructor(){
    //    this.pacientId = 4;
    //     this.begining = "2020-06-09T00:00";
    //     this.ending= "2020-06-09T24:00";
    //    this.room= 101;
    }
    // odabrani termin
    
    room:number;
    pacientId: number;
    date:string;
    begining:string;
    ending:string;
    doctorid:string
    mdoctors:any;
    start: string;
    
}