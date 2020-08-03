import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
@Component({
  selector: 'app-new-examination-dialog',
  templateUrl: './new-examination-dialog.component.html',
  styleUrls: ['./new-examination-dialog.component.css']
})
export class NewExaminationDialogComponent  {
  constructor(
    public dialogRef: MatDialogRef<NewExaminationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}
    
  hasOperation= false;
  setOperation(){this.hasOperation = true;}
  onNoClick(): void {
    this.dialogRef.close();
  }
}
