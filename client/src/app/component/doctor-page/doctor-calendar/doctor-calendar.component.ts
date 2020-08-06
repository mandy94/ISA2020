import { Component, OnInit } from '@angular/core';
import { ApiService, ConfigService } from 'app/service';
import * as moment from 'moment';
import { concat } from 'rxjs/operators/concat';


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

  }
  dayView = false;
  getByDay() {
    let dategenerator = moment();
    let id = 1;
    let requestDate = dategenerator.format("DD.MM.YYYY") + '/' + dategenerator.add(1, 'days').format('DD.MM.YYYY');
    let formatedRequestDate = '/' + requestDate.split('.').join("");
    this.api.get(this.conf.api_url + "/room-schedule/" + id + formatedRequestDate)
      .subscribe(data => {
        this.dedicatedRoom = data;
        this.weekView = false;
        this.monthView = false;
        this.dayView = true;

      });
  }
  weekView = false;
  getByWeek() {
    let dategenerator = moment().startOf('week');
    let id = 1;
    let requestDate = dategenerator.format("DD.MM.YYYY") + '/' + dategenerator.add(7, 'days').format('DD.MM.YYYY');
    let formatedRequestDate = '/' + requestDate.split('.').join("");
    this.api.get(this.conf.api_url + "/room-schedule/" + id + formatedRequestDate)
      .subscribe(data => {
        this.dedicatedRoom = data;
        this.dayView = false;
        this.monthView = false;
        this.weekView = true;
      });
  }
  monthView = false;
  lastMonth;
  wholeMonth;
  getByMonth() {
    let dategenerator = moment().startOf('month');
    let id = 1;
    let requestDate = dategenerator.format("DD.MM.YYYY") + '/' + dategenerator.add(1, 'month').format('DD.MM.YYYY');
    let formatedRequestDate = '/' + requestDate.split('.').join("");
    this.api.get(this.conf.api_url + "/room-schedule/" + id + formatedRequestDate)
      .subscribe(data => {
        this.dedicatedRoom = data;
        this.weekView = false;
        this.dayView = false;
        
        dategenerator = moment().startOf('month');
        requestDate = dategenerator.startOf('week').format('DD.MM.YYYY') + '/' + moment().startOf('month').format("DD.MM.YYYY");
        formatedRequestDate = '/' + requestDate.split('.').join("");
        
        this.api.get(this.conf.api_url + "/room-schedule/" + id + formatedRequestDate)
          .subscribe(data => {
            this.lastMonth = data;            
            this.wholeMonth= this.lastMonth.timeTable.concat(this.dedicatedRoom.timeTable);
            console.log(this.wholeMonth);            
            this.monthView = true;

          });
      });


  }
  newWeek(date) {
    let monday = new Date(date);//moment(date).format('DD.MM.YYYY');
    return monday.getDay() === 1;
  }
}
