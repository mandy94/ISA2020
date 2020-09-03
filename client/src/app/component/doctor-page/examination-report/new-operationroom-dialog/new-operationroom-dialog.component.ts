import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService, ConfigService, UserService } from 'app/service';
import { FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';
import { startWith, map } from 'rxjs/operators';
import { Room, Appointment } from '../report';
import * as moment from 'moment';

@Component({
  selector: 'app-new-operationroom-dialog',
  templateUrl: './new-operationroom-dialog.component.html',
  styleUrls: ['./new-operationroom-dialog.component.css']
})
export class NewOperationroomDialogComponent {
  
  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private formBuilder: FormBuilder,
   @Inject(MAT_DIALOG_DATA) public data: any) {                       
  }
  mandatoryDoctorsControl = new FormControl('');
  ngOnInit() {
    this.groupControl =this.formBuilder.group({
      roomControl : new FormControl({name:''}),
      dateControl :  new FormControl(moment())
    });
    
    this.loadData();
    this.filteredOptions = this.groupControl.get("roomControl").valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value)),
        
      );
  
  }

  isAvailabe;
  availableMandatoryDoctorList;
  doctorsSchedule;

  filled(){
    return this.groupControl.get("roomControl").value != '' && !this.groupControl.get("dateControl").valid ? false : true;
  }
  loadData() {
    this.apiService.get(this.config.api_url + '/operation-rooms/all').subscribe(data => this.roomList = data);
  }
  showRoomSchedule(){
    
    let selDate = this.formatDate(this.groupControl.get("dateControl").value);
    
    this.apiService.get(this.config.api_url + '/operation-room/' 
                      + this.groupControl.get("roomControl").value.id 
                      + '/mandatory-doctors').subscribe(data => this.availableMandatoryDoctorList = data);
    this.apiService.get(this.config.api_url + '/available-doctor-list/room/' +
                      + this.groupControl.get("roomControl").value.id 
                      +'/' + selDate).subscribe(data => {
                          this.doctorTermTable = data;                             
                      });
    
  }

  checkDoctorsAvaialbleTime(doctor){
    this.apiService.get(this.config.api_url + '/doctor/'+ doctor.id
                        +'/is-available/'+ this.formatDate(this.groupControl.get("dateControl").value)).subscribe(data => doctor.schedule = data);
  }
  makeRequest(selTerm){
    let that = this;
    this.availableMandatoryDoctorList.forEach(doctor => {
      let appointment ={
        pacientId : that.data.pacient.id,
        doctorId : doctor.id,
        room: that.groupControl.get("roomControl").value.id,
        date: that.formatDateWithDots(this.groupControl.get("dateControl").value),
        begining: selTerm.start,
        ending: selTerm.ending
        
      }
      this.apiService.post(this.config.api_url + '/appointment/room/new',appointment).subscribe((data)=>
        console.log("Poslali doktoru: " + doctor + data)
      );
    });
    
  }
  
  termTable = {
    available : [],
    notavailable: []
  }
  doctorTermTable;
  roomList;  
  groupControl:FormGroup;
  
  // Stuff for autocomplete
  filteredOptions: Observable<string[]>;
 
  formatDate(input:any){  
    return moment(input).format("DDMMYYYY");    
  }
  formatDateWithDots(input:any){  
    return moment(input).format("DD.MM.YYYY");    
  }
  private _filter(value:string): string[] {
    const filterValue = value.toLowerCase();    
    return this.roomList.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
  }
  displayRoomAs(room){
    return room && room.name ? room.name : '';
  }
}
