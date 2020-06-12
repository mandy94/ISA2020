import { Component, OnInit, Input, ViewChild } from '@angular/core';
import timeGridPlugin from '@fullcalendar/timegrid';
import momentPlugin from '@fullcalendar/moment';
import dayGridPlugin from '@fullcalendar/daygrid';


import 'fullcalendar';
import { ApiService, ConfigService } from 'app/service';



@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})

export class CalendarComponent implements OnInit {
  eventList = []
  calendarPlugins = [ timeGridPlugin ,dayGridPlugin,momentPlugin ]; // important!

  @Input() data_url :string;
  @ViewChild('calendar') calendarComponent: any;
  
  constructor(private apiService: ApiService,
    private config: ConfigService) {
    
  }
  ngOnInit() {  
    console.log(this.data_url)
      this.apiService.get(this.config.api_url + "/appointment/room/" +this.data_url )
      .subscribe(data => {
        this.eventList = data;
        let c = this.calendarComponent.getApi();
        c.render();
        
    });
         
  }
}