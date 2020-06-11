import { Component, OnInit, Input } from '@angular/core';

import 'fullcalendar';

import { Calendar } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})

export class CalendarComponent implements OnInit {

  
  
  constructor() {
    
  }
  ngOnInit() {
    var calendarEl = document.getElementById('full-calendar');
  
    var calendar = new Calendar(calendarEl, {
      plugins: [ dayGridPlugin ]
    });
  
    calendar.render(); 
  }


}
