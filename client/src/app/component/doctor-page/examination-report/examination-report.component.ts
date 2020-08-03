import { Component, OnInit } from '@angular/core';
import { ApiService, ConfigService, UserService } from 'app/service';

import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { FormControl } from '@angular/forms';
import { formatDate } from '@angular/common';
import { Report, Room, Appointment, Medicine } from './report';
import { EventEmitterService } from 'app/service/event-emitter.service';
import { NewExaminationDialogComponent } from './new-examination-dialog/new-examination-dialog.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-examination-report',
  templateUrl: './examination-report.component.html',
  styleUrls: ['./examination-report.component.css'],


})
export class ExaminationReportComponent implements OnInit {
  hasOperation = false;
  constructor(private apiService: ApiService,
    private config: ConfigService,
    private userService: UserService,
    private eventEmitterService: EventEmitterService,
    public dialog: MatDialog
  ) { }
  
  newExamination = {};
 
  openDialog(): void {
    const dialogRef = this.dialog.open(NewExaminationDialogComponent, {
      width: '700px',
      data: this.newExamination
    });

    dialogRef.afterClosed().subscribe(result => {      
      
    });
  }
  patiens: any;
  filteredOptions: Observable<any>;
  myControl = new FormControl();
  pacient_jmbg: String;
  currentPacient: any;
  allVisits: any;
  today: any;
  mandatoryDoctorList; any;
  searchMessage:string;

  displayedColumns = ['date', 'diagnose', 'details', 'meds','doctor'];
  isJMBGselected(){
    if(this.pacient_jmbg === undefined)
    return false;
    return true;
  }
  private _filter(value: string): string[] {
    const filterValue = value;

    return this.patiens.filter(option => option.toString().indexOf(filterValue) === 0);
  }
  hasPermission(){
    // ako je izabrani doktor
    return true;
  }
  getPacientByJMBG() {
    this.apiService.get(this.config.api_url + '/user/pacient/' + this.pacient_jmbg)
      .subscribe((data) => {
        if(data === null)
        this.searchMessage="Ne postoji pacijent sa trazenim JMBG-om. Probajte ponovo";
        else{
          this.searchMessage = null;
        this.currentPacient = data;
        }
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
  loggedDoctor:any;
  ngOnInit() {
    this.apiService.get(this.config.api_url + '/user/pacient/jmbg')
      .subscribe((data) => {
        this.patiens = data;
        this.userService.getMyInfo().subscribe(data =>{
            this.loggedDoctor = data;    
            this.refreshCalendar("");
           this.new_report.doctorid = data.id});
           this.filteredOptions = this.myControl.valueChanges.pipe(
          startWith(''),
          map(value => this._filter(value))
        );
      });
    this.today = formatDate(new Date(), 'dd/MM/yyyy', 'en');
   
    this.eventEmitterService.
    invoker.subscribe((name: string) => {
      this.testAvaialbility();
    });
    
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

  createAppointent() {
    this.appointnent = new Appointment();
    this.appointnent.pacientId = this.currentPacient.id;
    this.appointnent.room = this.selected_room.id;

    this.apiService.post(this.config.api_url + '/operation-room/new-appointment', this.appointnent).subscribe(() => { this.appointnent = null;});
  }
  pickedDate(){
    
    return this.selected_room ? true : false;
  }
  availableTimeList: any;
  selected_time: any;
  getRoomTime(){
    if(this.selected_room!=null)
    this.apiService.get(this.config.api_url + '/time/room/' + this.selected_room.id)
      .subscribe(data => { this.availableTimeList = data; this.refreshCalendar(this.selected_room.id.toString());});
    
  }
  getMandatoryDoctors(){
    this.apiService.get(this.config.api_url + '/operation-room/' + this.selected_room.id + '/mandatory-doctors')
    .subscribe(data => this.mandatoryDoctorList = data);
  }
  onRoomChange(){
    this.getRoomTime();
    this.getMandatoryDoctors();
    this.refreshCalendar(this.config.api_url + "/appointment/room/" + this.selected_room.id.toString());
  }
  
  createAppointment(){
  
    if(this.selected_room === null || this.selected_room === undefined)
    { 
      alert("Morate odabrati sobu prvi");
      return;
    } 
    let data = new Appointment();
    data.room = this.selected_room.id;
    data.start = this.selected_time.start;
    data.ending = this.selected_time.ending;
    data.pacientId = this.currentPacient.id;
    data.doctorid = this.loggedDoctor.id;
    data.mdoctors = this.mandatoryDoctorList;
   
      this.eventEmitterService.createAppointment(data);
  }
  

//  // ne koristi se nigde xd
  checkAvailablilty() {
    this.getRoomTime();
    this.apiService.get(this.config.api_url + '/operation-rooms/availability/' + this.selected_room.id)
      .subscribe(data => { 
        if(data && data.lenght >0)
        this.calendar = data;
        else
        this.calendar = "SVi termini su slobodni"; });
  }

   testAvaialbility(){
     if( this.eventEmitterService.pickedDate!= null){
    this.apiService.get(this.config.api_url +
        '/operation-room/'+ this.selected_room.id+
        '/availability/' + this.eventEmitterService.pickedDate)
    .subscribe((data) => {
         this.availableTimeList = data;
    });
  }
  }
  
  nextVisit(){
    this.refreshCalendar(this.config.api_url + "/doctor/scheduler/" + this.loggedDoctor.id);

  }
  showRoomSchedule(){
    this.refreshCalendar(this.config.api_url + "/appointment/room/"+ this.selected_room.id);
  }
refreshCalendar(new_url: string){
  this.eventEmitterService.calendarRefresher(new_url);
  }
}
