import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Medicine } from 'app/component/doctor-page/examination-report/report';
import { CodebookService } from 'app/service';
import { AddNewItemDialogComponent } from '../add-new-item-dialog/add-new-item-dialog.component';
import { MatDialog } from '@angular/material';
import { ApiService, ConfigService } from 'app/service';


@Component({
  selector: 'app-medication',
  templateUrl: './medication.component.html',
  styleUrls: ['./medication.component.css']
})
export class MedicationComponent implements OnInit {

  constructor(private codebookservice : CodebookService,private apiService: ApiService,
    private conf: ConfigService,public dialog: MatDialog) { }

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
  delete(med){
    this.apiService.delete(this.conf.api_url + "/codes/med", med.id).subscribe(()=>    this.getMeds());  
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(AddNewItemDialogComponent, {
      width: '350px',
      data: { resourceName:"leka" }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getMeds();
    });
  }
}
