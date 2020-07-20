import { Component, OnInit, Input, ViewChild } from '@angular/core';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin, { ThirdPartyDraggable } from '@fullcalendar/interaction'; // for selectable
import momentPlugin from '@fullcalendar/moment';
import dayGridPlugin from '@fullcalendar/daygrid';


import 'fullcalendar';
import { ApiService, ConfigService } from 'app/service';
import { EventEmitterService } from 'app/service/event-emitter.service';
import { Appointment, MailMessage } from 'app/component/doctor-page/examination-report/report';


@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})

export class CalendarComponent implements OnInit {
  eventList = []
  calendarPlugins = [timeGridPlugin, dayGridPlugin, momentPlugin, interactionPlugin]; // important!

  @Input() data_url: string;
  @ViewChild('calendar') calendarComponent: any;

  constructor(private apiService: ApiService,
    private config: ConfigService,
    private eventEmitterService: EventEmitterService) {

  }
  c: any;
  ngOnInit() {

     this.eventEmitterService.
        invoker.subscribe((name: string) => {
          this.refreshCalendar(this.eventEmitterService.data);
        });
        
    

    this.eventEmitterService.
      invoker.subscribe((name: string) => { this.createAppointment(this.eventEmitterService.room); })


  }
  createAppointment(data: Appointment) {
    if (data === null || data === undefined)
      return;
    if (this.clickedDate === null || this.clickedDate === undefined) {
      alert("Morate prvo odabrati datum");
      return;
    }

    data.begining =  data.start;
    data.ending =  data.ending;
    data.date = this.clickedDate;
    this.apiService.post(this.config.api_url + '/appointment/room/new', data)
      .subscribe(() => {
        
        let content = "Postovani, obavestavamo Vas da vam je zakazana operacija za datum " + this.clickedDate + " i vremenski termin u periodu od "+ data.start + " do" +data.ending + ". Ocekujemo vas. Pozdrav.";
        let msgbody = new MailMessage(data.pacientId, "Zakazivanje operacije", content);
        for(let md of data.mdoctors)
          {
            console.log(md);
            let content = "Postovani/na "+ md.name + ", obavestavamo Vas da vam je zakazana operacija za datum " + this.clickedDate + " i vremenski termin u periodu od "+ data.start + " do" +data.ending + ". Ocekujemo vas. Pozdrav.";
            let msgbody = new MailMessage(md.id, "Zakazivanje operacije", content);
             this.apiService.post(this.config.api_url + '/email/send', msgbody).subscribe();
          }
        this.apiService.post(this.config.api_url + '/email/send', msgbody)
        .subscribe(()=>{
          this.refreshCalendar(data.room.toString());
          data = null;
        });
        
      });
  }
  refreshCalendar(new_url) {
    
    if(new_url == null || new_url == "")
    {
      this.c = this.calendarComponent.getApi();
      this.c.render();
    }else
    this.apiService.get(new_url)
      .subscribe(data => {
        
        this.eventList = data;
        this.c = this.calendarComponent.getApi();
        this.c.render();
        console.log("Calendar refreshed from url: " + new_url);

      });
  }
  clickedDate: string;

  dateClick($event) {
    this.clickedDate = $event.dateStr;
    console.log('Clicked on: ' + $event.dateStr);
    this.eventEmitterService.testAvailability($event.dateStr);
  }

}