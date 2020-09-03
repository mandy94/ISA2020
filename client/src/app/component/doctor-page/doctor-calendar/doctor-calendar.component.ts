import { Component, OnInit } from '@angular/core';
import { ApiService, ConfigService } from 'app/service';
import * as moment from 'moment';
import { concat } from 'rxjs/operators/concat';
import { User } from 'app/shared/models/user';
import { timeout } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-doctor-calendar',
  templateUrl: './doctor-calendar.component.html',
  styleUrls: ['./doctor-calendar.component.css']
})
export class DoctorCalendarComponent implements OnInit {

  constructor(private api: ApiService,
              private conf: ConfigService,
              private router: Router) { }
  dedicatedRoom;
  ngOnInit() {
    moment.updateLocale("en", { week: { dow: 1 } });
    this.currentDay = moment();
    this.getDay();

  }
  loadingData = false;
  dayView = false;
  monthView = false;
  lastMonth;
  wholeMonth;
  currentDay;
  currentWeek;
  fromDateRequest;
  toDateRequest;
  currentMonth;
  doctor = { id: 1 };

  makeRequest() {

    let formatedRequestDate = '/' + (this.fromDateRequest + '/' + this.toDateRequest).split('.').join("");    
    return this.api.get(this.conf.api_url + "/room-schedule/" + this.doctor.id + formatedRequestDate)

  }
  isPrevous = false; // kada se ide u nazad
  weekView = false;

  showPacientAppointment(jmbg) {
   
        this.router.navigate(['/pacient-examination', jmbg]);   
  }
  
  getDay() {
    this.loadingData = true;
    this.fromDateRequest = moment().format('DD.MM.YYYY');
    this.toDateRequest = moment().add(1, 'day').format('DD.MM.YYYY');
    this.currentDay = moment().add(1, 'day');
    this.makeRequest().subscribe(data => { this.dedicatedRoom = data, this.loadingData = false; });
    this.weekView = false;
    this.monthView = false;
    this.dayView = true;
  }
  getWeek() {
    this.loadingData = true;
    
    this.currentDay = moment().startOf('week').add(1, 'week');
    this.fromDateRequest = moment().startOf('week').format("DD.MM.YYYY");
    this.toDateRequest = moment().startOf('week').add(7, 'days').format('DD.MM.YYYY');

    this.makeRequest().subscribe(data => { this.dedicatedRoom = data, this.loadingData = false; });
    this.dayView = false;
    this.monthView = false;
    this.weekView = true;
  }
  getMonth() {

    this.loadingData = true;
    this.currentDay = moment().startOf('month');
    this.currentMonth = this.currentDay.format('MMMM');

    this.toDateRequest = this.currentDay.format("DD.MM.YYYY");
    this.fromDateRequest = moment().startOf('month').startOf('week').format('DD.MM.YYYY')
    this.makeRequest().subscribe(data => { this.lastMonth = data });


    this.fromDateRequest = this.currentDay.format("DD.MM.YYYY");
    this.toDateRequest = moment().startOf('month').add(1, 'month').format('DD.MM.YYYY');
    this.makeRequest().subscribe(data => { this.dedicatedRoom = data, this.loadingData = false });
    this.dayView = false;
    this.monthView = true;
    this.weekView = false;


  }
  next() {
    this.loadingData = true;
    let period;
    let displayDates;
    if (this.dayView) period = 'day';
    if (this.weekView) period = 'week';
    if (this.monthView) {
      period = 'month';
      this.currentDay.add(1, 'month').startOf('month');

      this.currentMonth = this.currentDay.format('MMMM');

      displayDates = this.currentDay;
      this.toDateRequest = displayDates.format('DD.MM.YYYY');
      this.fromDateRequest = displayDates.startOf('week').format('DD.MM.YYYY')
      this.makeRequest().subscribe(data => { this.lastMonth = data });

      displayDates = this.currentDay.endOf('month').add(1, 'day');
      console.log("Starts from" + displayDates.format('DD.MM.YYYY'));
      this.fromDateRequest = displayDates.format('DD.MM.YYYY');
      this.toDateRequest = displayDates.endOf('month').format('DD.MM.YYYY');
      this.makeRequest().subscribe(data => { this.dedicatedRoom = data, this.loadingData = false; });
      this.isPrevous = false;
      return;
    }

    this.fromDateRequest = this.currentDay.format('DD.MM.YYYY');
    this.toDateRequest = this.currentDay.add(1, period).format('DD.MM.YYYY');
    this.makeRequest().subscribe(data => { this.dedicatedRoom = data, this.loadingData = false; });
    this.isPrevous = false;
  }
  previous() {
    this.loadingData = true;
    let period;
    let displayDates;
    if (this.dayView) period = 'day';
    if (this.weekView) period = 'week';
    if (this.monthView) {
      period = 'month';
      this.currentDay.subtract(this.isPrevous === true ? 1 : 2, period).format('DD.MM.YYYY');
      this.currentMonth = this.currentDay.format('MMMM');
      displayDates = this.currentDay;
      this.toDateRequest = displayDates.format('DD.MM.YYYY');
      this.fromDateRequest = displayDates.startOf('week').format('DD.MM.YYYY')
      this.makeRequest().subscribe(data => { this.lastMonth = data });


      this.fromDateRequest = this.toDateRequest;
      this.toDateRequest = displayDates.startOf('month').add(1, 'month').format('DD.MM.YYYY');
      this.makeRequest().subscribe(data => { this.dedicatedRoom = data, this.loadingData = false; });
      this.isPrevous = true;
      return;
    }

    this.toDateRequest = this.currentDay.subtract(this.isPrevous === true ? 0 : 1, period).format('DD.MM.YYYY');
    this.fromDateRequest = this.currentDay.subtract(1, period).format('DD.MM.YYYY');
    this.makeRequest().subscribe(data => { this.dedicatedRoom = data, this.loadingData = false; });
    this.isPrevous = true;
  }
  newWeek(date) {
    let monday = new Date(date);//moment(date).format('DD.MM.YYYY');
    return monday.getDay() === 1;
  }
  DayList = ["Ponedeljak", "Utorak", "Sreda", "Cetvrtak", "Petak", "Subota", "Nedelja"];
  printDayAsAString(date) {
    let day = moment(date, 'DD.MM.YYYY');

    if (day.day() === 1) return "Ponedeljak";
    if (day.day() === 2) return "Utorak";
    if (day.day() === 3) return "Sreda";
    if (day.day() === 4) return "Cetvrtak";
    if (day.day() === 5) return "Petak";
    if (day.day() === 6) return "Subota";
    if (day.day() === 0) return "Nedelja";

  }
}
