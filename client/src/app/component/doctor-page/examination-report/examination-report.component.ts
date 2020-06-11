import { Component, OnInit } from '@angular/core';
import { ApiService, ConfigService, UserService } from 'app/service';

import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { FormControl } from '@angular/forms';
import { formatDate } from '@angular/common';
import { Report, Room, Appointment, Medicine } from './report';


@Component({
  selector: 'app-examination-report',
  templateUrl: './examination-report.component.html',
  styleUrls: ['./examination-report.component.css'],


})
export class ExaminationReportComponent implements OnInit {

  constructor(private apiService: ApiService,
    private config: ConfigService,
    private userService: UserService
  ) { }
  patiens: any;
  filteredOptions: Observable<any>;
  myControl = new FormControl();
  pacient_jmbg: String;
  currentPacient: any;
  allVisits: any;
  today: any;

  private _filter(value: string): string[] {
    const filterValue = value;

    return this.patiens.filter(option => option.toString().indexOf(filterValue) === 0);
  }
  getPacientByJMBG() {
    this.apiService.get(this.config.api_url + '/user/pacient/' + this.pacient_jmbg)
      .subscribe((data) => {
        this.currentPacient = data;
      });

  }
  getPacientExaminationReports() {
    this.apiService.get(this.config.api_url + '/examination-report/pacient/' + this.pacient_jmbg)
      .subscribe((data) => {
        this.allVisits = data;
      });
  }
  getCodebooks() {
    this.apiService.get(this.config.api_url + '/codes/diagnoses/all')
      .subscribe((data) => {
        this.diagnoseList = data;
      });
    this.apiService.get(this.config.api_url + '/codes/medications/all')
      .subscribe((data) => {
        this.medList = data;
      });
      this.apiService.get(this.config.api_url + '/codes/therapies')
      .subscribe((data)=>{
        this.therapyList = data        
      })
  }
  getOperationRooms() {
    this.apiService.get(this.config.api_url + '/operation-rooms/all')
      .subscribe(data => {
        this.operationRoomList = data;
      });
  }
  onSearchClicked() {
    this.getPacientByJMBG();
    this.getPacientExaminationReports();
    this.getCodebooks();
    this.getOperationRooms();
  }
  diagnoseList: any;
  medList: any;
  firstVisit() {
    if (this.allVisits != null)
      return this.allVisits.length == 0 ? true : false;
  }
  addAnotherTherapy(){
    this.changedInput = true;
    this.new_report.therapies.push(this.selected_therapy); 
    
  }
  addAnotherRecepie() {
    this.changedInput = true;
    this.new_report.medication.push(this.selected_med);
    
    
  }
  changedInput: boolean;
  inputHaveChanged(){
    return this.changedInput;
  }
  ngOnInit() {
    this.apiService.get(this.config.api_url + '/user/pacient/jmbg')
      .subscribe((data) => {
        this.patiens = data;
        this.userService.getMyInfo().subscribe(data => this.new_report.doctorid = data.id);
        this.filteredOptions = this.myControl.valueChanges.pipe(
          startWith(''),
          map(value => this._filter(value))
        );
      });
    this.today = formatDate(new Date(), 'dd/MM/yyyy', 'en');
  }
  appointnent: Appointment;
  selected_meds = new Array<any>(); // FOR VIEW ONLY
  selected_med: Medicine;
  selected_room: Room;
  selected_diagnose: any;
  selected_therapy: any;
  description: string;
  new_report = new Report();

  saveReport() {

    this.new_report.details = this.description;
    this.new_report.pacientid = this.currentPacient.id;

    this.apiService.post(this.config.api_url + '/examination-report/add', this.new_report)
      .subscribe(() => this.getPacientExaminationReports());
  }
  operationRoomList: any;
  therapyList: any;
  calendar: any;

  test() {
    this.appointnent = new Appointment();
    this.appointnent.pacientId = this.currentPacient.id;
    this.appointnent.room = this.selected_room.id;

    this.apiService.post(this.config.api_url + '/operation-room/new-appointment', this.appointnent).subscribe(() => this.checkAvailablilty);
  }
  checkAvailablilty() {

    this.apiService.get(this.config.api_url + '/operation-rooms/availability/' + this.selected_room.id)
      .subscribe(data => { 
        if(data && data.lenght >0)
        this.calendar = data;
        else
        this.calendar = "SVi termini su slobodni"; });
  }
}
