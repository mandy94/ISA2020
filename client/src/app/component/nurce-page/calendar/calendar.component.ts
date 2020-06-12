import { Component, OnInit, Input, ViewChild } from '@angular/core';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin, { ThirdPartyDraggable } from '@fullcalendar/interaction'; // for selectable
import momentPlugin from '@fullcalendar/moment';
import dayGridPlugin from '@fullcalendar/daygrid';


import 'fullcalendar';
import { ApiService, ConfigService } from 'app/service';
import { EventEmitterService } from 'app/service/event-emitter.service';
import { Appointment } from 'app/component/doctor-page/examination-report/report';



@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})

export class CalendarComponent implements OnInit {
  eventList = []
  calendarPlugins = [ timeGridPlugin ,dayGridPlugin,momentPlugin,interactionPlugin ]; // important!

  @Input() data_url :string;
  @ViewChild('calendar') calendarComponent: any;
  
  constructor(private apiService: ApiService,
    private config: ConfigService,
    private eventEmitterService: EventEmitterService    ) {
      
  }
  c:any;
  ngOnInit() {  
    
    
    if (this.eventEmitterService.subsVar==undefined) {    
      this.eventEmitterService.subsVar = this.eventEmitterService.    
      invoker.subscribe((name:string) => {    
        this.refreshCalendar(this.eventEmitterService.data);    
        
      }); }
      
      this.eventEmitterService.
      invoker.subscribe((name:string)=>
      { this.createAppointment(this.eventEmitterService.room);})
    
    
  }
  createAppointment(data : Appointment){
    data.begining = this.clickedDate; 
    data.ending = this.clickedDate;
    this.apiService.post(this.config.api_url + '/appointment/room/new', data)
     .subscribe(() => {       
       this.refreshCalendar(data.room.toString())
      });
  }
refreshCalendar(new_url){
  this.apiService.get(this.config.api_url + "/appointment/room/" +new_url )
      .subscribe(data => {
      this.eventList = data;  
      this.c = this.calendarComponent.getApi();           
      this.c.render();
      console.log("Calendar refreshed from url: /appointment/room/" +new_url  );
            
    });
}
clickedDate:string;

dateClick($event){
   this.clickedDate = $event.dateStr;
   console.log('Clicked on: ' + $event.dateStr);
  }

}