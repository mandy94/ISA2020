import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService, ConfigService, UserService } from 'app/service';
import { FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';
import { startWith, map } from 'rxjs/operators';
import { Room } from '../report';
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
    private formBuilder: FormBuilder
  ) { }
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
  filled(){
    return this.groupControl.get("roomControl").value != '' && !this.groupControl.get("dateControl").valid ? false : true;
  }
  loadData() {
    this.apiService.get(this.config.api_url + '/operation-rooms/all').subscribe(data => this.roomList = data);
  }
  showRoomSchedule(){
    console.log(this.formatDate(this.groupControl.get("dateControl").value));
    
    this.apiService.get(this.config.api_url + '/operation-room/' 
                      + this.groupControl.get("roomControl").value.id 
                      + '/availability/' +this.formatDate(this.groupControl.get("dateControl").value)).subscribe(data => this.termTable = data);

  }
  
  termTable = {
    available : [],
    notavailable: []
  }
  roomList;  
  groupControl:FormGroup;
  
  // Stuff for autocomplete
  filteredOptions: Observable<string[]>;
 
  formatDate(input:any){  
    return moment(input).format("DDMMYYYY");    
  }
  private _filter(value:string): string[] {
    const filterValue = value.toLowerCase();
    console.log("promena");
    return this.roomList.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
  }
  displayRoomAs(room){
    return room && room.name ? room.name : '';
  }
}
