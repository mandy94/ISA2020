import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Medicine } from 'app/component/doctor-page/examination-report/report';
import { CodebookService } from 'app/service';

@Component({
  selector: 'app-medication',
  templateUrl: './medication.component.html',
  styleUrls: ['./medication.component.css']
})
export class MedicationComponent implements OnInit {

  constructor(private codebookservice : CodebookService) { }

  ngOnInit() {
    this.getMeds();
  }
  
  meds: any;
  newMed = new FormControl(' ');
  displayedColumns = ['id','dose', 'name', 'edit', 'delete'];
  getMeds(){
    this.codebookservice.getCodesForMedication()
    .subscribe( data => {this.meds = data});
  }
  postMed(){
    let med = new Medicine();
    med.name  = this.newMed.value;
    this.codebookservice.postMeds(med).subscribe(data => this.meds = data);
  }
}
