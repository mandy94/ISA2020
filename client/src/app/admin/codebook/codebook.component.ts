import { Component, OnInit } from '@angular/core';
import { CodebookService } from 'app/service';
import { Diagnose, Therapy } from '.';
import { FormControl } from '@angular/forms';
import { Room, Medicine } from 'app/component/doctor-page/examination-report/report';

@Component({
  selector: 'app-codebook',
  templateUrl: './codebook.component.html',
  styleUrls: ['./codebook.component.css']
})
export class CodebookComponent implements OnInit {

  constructor( private codebookservice : CodebookService) { }

  diagnoses: any;
  therapies: any;
  rooms: any;
  meds: any;
  newDiagnose = new FormControl(' ');
  newTherapy = new FormControl(' ');
  newRoom = new FormControl(' ');
  newMed = new FormControl(' ');

  ngOnInit() {
    this.getDiagnoses();
    this.getMeds();
    this.getRooms();
    this.getTherapies();
  }
 // START OF DIAGNOSE CODE 
  getDiagnoses(){
    this.codebookservice.getCodesForDiagnoses()
    .subscribe( data => {this.diagnoses = data});
  }
  
  postDiagnose(){
    let diagnose = new Diagnose();
    diagnose.name = this.newDiagnose.value;    
    this.codebookservice.postDiagnose(diagnose).subscribe((data) => this.diagnoses = data);
  }
// END OF DIAGNOSE CODES
// START OF THERAPY CODES
  getTherapies(){
    this.codebookservice.getCodesForTherapies()
    .subscribe( data => {this.therapies = data});

  }
  postTherapy(){
    let therapy = new Therapy();
    therapy.name = this.newTherapy.value;
    
    this.codebookservice.postTherapy(therapy).subscribe((data) => this.therapies = data);
  }
// END OF THERAPY CODES
// START OD ROOMS CODES
  getRooms(){
    this.codebookservice.getCodesForOperationRooms()
    .subscribe( data => {this.rooms = data});

  }
  postRoom(){
    let room = new Room();
    room.name = this.newRoom.value;
      this.codebookservice.postRoom(room).subscribe(data => this.rooms = data);
  }
  
// END OF ROOMS COODE
//START OF MEDS CODES
  getMeds(){
    this.codebookservice.getCodesForMedication()
    .subscribe( data => {this.meds = data});
  }
  postMed(){
    let med = new Medicine();
    med.name  = this.newMed.value;
    this.codebookservice.postMeds(med).subscribe(data => this.meds = data);
  }
  // END OF MEDS CODES
}
