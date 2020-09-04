import { Component, OnInit } from '@angular/core';
import { Diagnose } from 'app/component/doctor-page/examination-report/report';
import { CodebookService } from 'app/service';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ApiService, ConfigService } from 'app/service';

import { AddNewItemDialogComponent } from '../add-new-item-dialog/add-new-item-dialog.component';

@Component({
  selector: 'app-diagnoses',
  templateUrl: './diagnoses.component.html',
  styleUrls: ['./diagnoses.component.css']
})
export class DiagnosesComponent implements OnInit {

  constructor(private codebookservice: CodebookService,
    private apiService: ApiService,
    private conf: ConfigService,
    public dialog: MatDialog) { }

  diagnoses: any;
  displayedColumns = ['id', 'code', 'name', 'edit', 'delete'];
  newDiagnose = new FormControl('');
  ngOnInit() {
    this.getDiagnoses();
  }

  getDiagnoses() {
    this.codebookservice.getCodesForDiagnoses()
      .subscribe(data => { this.diagnoses = data });
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(AddNewItemDialogComponent, {
      width: '350px',
      data: { resourceName: "dijagnoze" }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getDiagnoses();
    });
  }
  delete(diagnose) {
    this.apiService.delete(this.conf.api_url + "/codes/diagnose", diagnose.id).subscribe(() => this.getDiagnoses());


  }
  postDiagnose() {
    let diagnose = new Diagnose();
    diagnose.name = this.newDiagnose.value;
    this.codebookservice.postDiagnose(diagnose).subscribe((data) => this.diagnoses = data);
  }
}
