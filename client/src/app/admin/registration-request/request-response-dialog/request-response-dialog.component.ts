import { Component, OnInit, Inject } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-request-response-dialog',
  templateUrl: './request-response-dialog.component.html',
  styleUrls: ['./request-response-dialog.component.css']
})
export class RequestResponseDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<RequestResponseDialogComponent>,
  ) {}

  requestResponseCtrl = new FormControl('');
  ngOnInit() {
  }
  submit(){
        this.dialogRef.close(this.requestResponseCtrl.value);
  }
  exit(){
    this.dialogRef.close();
  }
}
