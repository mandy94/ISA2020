import { Component, OnInit } from '@angular/core';
import { CodebookService } from 'app/service';
import { Diagnose, Therapy } from '.';
import { FormControl } from '@angular/forms';

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

  getDiagnoses(){
    this.codebookservice.getCodesForDiagnoses()
    .subscribe( data => {this.diagnoses = data});
  }
  
  postDiagnose(){
    let diagnose = new Diagnose();
    diagnose.name = this.newDiagnose.value;    
    this.codebookservice.postDiagnose(diagnose).subscribe((data) => this.diagnoses = data);
  }

  getTherapies(){
    this.codebookservice.getCodesForTherapies()
    .subscribe( data => {this.therapies = data});

  }
  postTherapy(){
    let therapy = new Therapy();
    therapy.name = this.newTherapy.value;
    this.codebookservice.postTherapy(therapy).subscribe((data) => this.therapies = data);
  }
  getRooms(){
    this.codebookservice.getCodesForOperationRooms()
    .subscribe( data => {this.rooms = data});

  }
  
  getMeds(){
    this.codebookservice.getCodesForMedication()
    .subscribe( data => {this.meds = data});

  }
}
