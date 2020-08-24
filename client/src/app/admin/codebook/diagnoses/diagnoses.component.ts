import { Component, OnInit } from '@angular/core';
import { Diagnose } from 'app/component/doctor-page/examination-report/report';
import { CodebookService } from 'app/service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-diagnoses',
  templateUrl: './diagnoses.component.html',
  styleUrls: ['./diagnoses.component.css']
})
export class DiagnosesComponent implements OnInit {

  constructor(private codebookservice : CodebookService) { }

  diagnoses: any;
  displayedColumns = ['id','code', 'name', 'edit', 'delete'];
  newDiagnose = new FormControl('');
  ngOnInit() {
    this.getDiagnoses();
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
}
