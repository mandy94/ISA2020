import { Injectable, EventEmitter } from '@angular/core';    
import { Subscription } from 'rxjs/internal/Subscription';  

@Injectable({
  providedIn: 'root'
})
export class EventEmitterService {

  invoker = new EventEmitter();    
  subsVar: Subscription;    
    
  room: any;
  data:any;
  public pickedDate:any;
    
  constructor() { }    
    
  calendarRefresher(data) {    
    this.data = data;
    this.invoker.emit();    
  }   

  createAppointment(room){
    this.room = room;
    this.invoker.emit();
  }

  testAvailability(date:string){
    this.pickedDate = date;
    this.invoker.emit();
  }
}
