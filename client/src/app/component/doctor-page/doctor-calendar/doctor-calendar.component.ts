import { Component, OnInit } from '@angular/core';
import { ApiService, ConfigService } from 'app/service';

@Component({
  selector: 'app-doctor-calendar',
  templateUrl: './doctor-calendar.component.html',
  styleUrls: ['./doctor-calendar.component.css']
})
export class DoctorCalendarComponent implements OnInit {

  constructor(private api: ApiService, private conf: ConfigService) { }
  dedicatedRoom;
  ngOnInit() {
    let id = 1;
    this.api.get(this.conf.api_url+"/room-schedule/day/"+ id)
          .subscribe(data => {
            this.dedicatedRoom = data;
          });
  }

  showWeek(){
    this.api.get(this.conf.api_url+"/room-schedule/week/"+ id)
          .subscribe(data => {
            this.dedicatedRoom = data;
          });
  }

}
