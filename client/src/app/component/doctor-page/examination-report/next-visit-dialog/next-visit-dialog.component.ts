import { Component, OnInit, Inject } from '@angular/core';
import { ApiService, ConfigService, UserService } from 'app/service';
import { FormBuilder, FormControl } from '@angular/forms';
import * as moment from 'moment';
import { injectTemplateRef } from '@angular/core/src/render3/view_engine_compatibility';
import { MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-next-visit-dialog',
  templateUrl: './next-visit-dialog.component.html',
  styleUrls: ['./next-visit-dialog.component.css']
})
export class NextVisitDialogComponent implements OnInit {
  groupControl: any;

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private formBuilder: FormBuilder,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: any) 
 { }

  ngOnInit() {
   this.apiService.get(this.config.api_url + '/operation-rooms/all')
                      .subscribe(data => this.roomList = data);
  }
  new_visit;
  roomList;
  myTimeTable;
  dateControl = new FormControl(moment());
  roomControl= new FormControl('');
  checkMe(){

    this.apiService.get(this.config.api_url +  '/doctor/' + this.data.doctor.id+
                       '/is-available/'+this.formatDate(this.dateControl.value))
                      .subscribe( retdata => this.myTimeTable = retdata);
                  
  }
  makeAnAppointment(time){
    this.new_visit = {
      doctorId: this.data.doctor.id,
      pacientId: this.data.pacient.id,
      date: this.formatDateWithDots(this.dateControl.value),
      begining: time.start,
      ending: time.ending,
      room: this.roomControl.value.id
    }
    console.log(this.new_visit)
    this.apiService.post(this.config.api_url + '/appointment/room/new', this.new_visit).subscribe();
  }
  formatDate(input:any){  
    return moment(input).format("DDMMYYYY");    
  }
  formatDateWithDots(input:any){  
    return moment(input).format("DD.MM.YYYY");    
  }}
