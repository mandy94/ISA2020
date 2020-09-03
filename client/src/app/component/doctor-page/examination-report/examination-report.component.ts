import { Component, OnInit } from '@angular/core';
import { ApiService, ConfigService, UserService } from 'app/service';

import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { FormControl } from '@angular/forms';
import { formatDate } from '@angular/common';
import { Report, Room, Appointment, Medicine } from './report';
import { EventEmitterService } from 'app/service/event-emitter.service';
import { NewExaminationDialogComponent } from './new-examination-dialog/new-examination-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { NextVisitDialogComponent } from './next-visit-dialog/next-visit-dialog.component';
import { NewOperationroomDialogComponent } from './new-operationroom-dialog/new-operationroom-dialog.component';
import { ReportDTO } from 'app/shared/models/other';
import { ActivatedRoute } from '@angular/router';
import { noUndefined } from '@angular/compiler/src/util';

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
    private activatedRoute: ActivatedRoute,
    private eventEmitterService: EventEmitterService,
    public dialog: MatDialog
  ) { }

  newExamination = new ReportDTO;
  newOperation = {};
  nextVisit = {};

  patiens: any;
  filteredOptions: Observable<any>;
  myControl = new FormControl();
  pacientJMBGCtrl = new FormControl('');
  currentPacient: any;
  allVisits: any;
  today: any;
  mandatoryDoctorList; any;
  searchMessage: string;
  appointnent: Appointment;
  selected_meds = new Array<any>(); // FOR VIEW ONLY
  selected_med: Medicine;
  selected_room: Room;
  selected_diagnose: any;
  selected_therapy: any;
  description: string;
  new_report = new Report();
  searchView = true;
  indexOrder = 1; // indexfor mat tab group

  nextVisitDialog(): void {
    const dialogRef = this.dialog.open(NextVisitDialogComponent, {
      width: '710px',
      data: { pacient: this.currentPacient, doctor: this.loggedDoctor }
    });

    dialogRef.afterClosed().subscribe(result => {

    });
  }
  newOperationDialog(): void {
    const dialogRef = this.dialog.open(NewOperationroomDialogComponent, {
      width: '710px',
      data: { pacient: this.currentPacient, doctor: this.loggedDoctor }
    });

    dialogRef.afterClosed().subscribe(result => {

    });
  }
  newExaminationDialog(): void {
    this.newExamination.pacient = this.currentPacient;
    this.newExamination.doctor = this.loggedDoctor;
    const dialogRef = this.dialog.open(NewExaminationDialogComponent, {
      width: '600px',
      data: { pacient: this.currentPacient, doctor: this.loggedDoctor }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getPacientExaminationReports();
    });
  }

  displayedColumns = ['date', 'diagnose', 'details', 'meds', 'doctor'];

  isJMBGselected() {
    if (this.pacientJMBGCtrl.value === undefined)
      return false;
    return true;
  }
  private _filter(value: string): string[] {
    const filterValue = value;

    return this.patiens.filter(option => option.toString().indexOf(filterValue) === 0);
  }
  hasPermission() {
    // ako je izabrani doktor
    return true;
  }
  getPacientByJMBG() {

    this.apiService.get(this.config.api_url + '/user/pacient/' + this.pacientJMBGCtrl.value)
      .subscribe((data) => {
        if (data === null)
          this.searchMessage = "Ne postoji pacijent sa trazenim JMBG-om. Probajte ponovo";
        else {
          this.searchMessage = null;
          this.currentPacient = data;

        }
      });

  }
  getPacientExaminationReports() {
    this.apiService.get(this.config.api_url + '/examination-report/pacient/' + this.pacientJMBGCtrl.value)
      .subscribe((data) => {
        this.allVisits = data;
      });
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
    this.getOperationRooms();
  }
  diagnoseList: any;
  medList: any;

  firstVisit() {
    if (this.allVisits != null)
      return this.allVisits.length == 0 ? true : false;
  }

  changedInput: boolean;
  inputHaveChanged() {
    return this.changedInput;
  }
  loggedDoctor: any;
  ngOnInit() {

    this.pacientJMBGCtrl.setValue(this.activatedRoute.snapshot.paramMap.get('jmbg'));    
    this.today = formatDate(new Date(), 'dd/MM/yyyy', 'en');
    
    if (this.pacientJMBGCtrl.value != undefined) {
      this.indexOrder = 2;
      this.onSearchClicked();
      this.searchView = false;
      return;
    }
    this.apiService.get(this.config.api_url + '/user/pacient/jmbg')
      .subscribe((data) => {
        this.patiens = data;
        this.userService.getMyInfo().subscribe(data => {
          this.loggedDoctor = data;
        });
        this.filteredOptions = this.myControl.valueChanges.pipe(
          startWith(''),
          map(value => this._filter(value))
        );
      });

  }

  operationRoomList: any;
  therapyList: any;
  calendar: any;

  createAppointent() {
    this.appointnent = new Appointment();
    this.appointnent.pacientId = this.currentPacient.id;
    this.appointnent.room = this.selected_room.id;

    this.apiService.post(this.config.api_url + '/operation-room/new-appointment', this.appointnent).subscribe(() => { this.appointnent = null; });
  }
  pickedDate() {

    return this.selected_room ? true : false;
  }
  availableTimeList: any;
  selected_time: any;
  
 


  // nextVisit(){
  //   this.refreshCalendar(this.config.api_url + "/doctor/scheduler/" + this.loggedDoctor.id);

  // }
  pacientDataControl = new FormControl('');
  tempNewPacientData;
  newPacientData() {
    this.tempNewPacientData = { bloodType: '', height: 0, weight: 0 };
  }
  saveChanges() {
    this.currentPacient.data = this.tempNewPacientData;
    this.tempNewPacientData = null;
  }
 
}
