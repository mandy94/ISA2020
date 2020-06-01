import { Component, OnInit } from '@angular/core';
import { CodebookService } from 'app/service';

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
  displayedColumns: string[] =['Sifra' , 'Naziv'];
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
  getTherapies(){
    this.codebookservice.getCodesForTherapies()
    .subscribe( data => {this.therapies = data});

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
