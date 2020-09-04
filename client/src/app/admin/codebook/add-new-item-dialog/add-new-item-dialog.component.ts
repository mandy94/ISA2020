import { Component, OnInit, Inject } from '@angular/core';
import { FormControl } from '../../../../../node_modulmore/@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ApiService, ConfigService } from 'app/service';

@Component({
  selector: 'app-add-new-item-dialog',
  templateUrl: './add-new-item-dialog.component.html'
})
export class AddNewItemDialogComponent implements OnInit {

  constructor(private apiService: ApiService,
    private conf: ConfigService,
    public dialogRef: MatDialogRef<AddNewItemDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  nameCotrl = new FormControl('');
  dosageCtrle = new FormControl('');
  codeCtrl = new FormControl('');
  ngOnInit() {
  }
  submitMeds() {

    let newMed = {
      name: this.nameCotrl.value,
      dosage: this.dosageCtrle.value,
    };
    this.apiService.post(this.conf.api_url + "/codes/med", newMed).subscribe();
  }
  submitTherapy() {
    let newTherpay = {
      name: this.nameCotrl.value,
    };
    this.apiService.post(this.conf.api_url + "/codes/therapy", newTherpay).subscribe();
  }
  submitDiagnose() {

    let newDiagnose = {
      name: this.nameCotrl.value,
      code: this.codeCtrl.value,
    };
    this.apiService.post(this.conf.api_url + "/codes/diagnose", newDiagnose).subscribe();

  }
  submitRoom(){
    let newRoom = {
      name: this.nameCotrl.value,
      code: this.codeCtrl.value,
    };
    this.apiService.post(this.conf.api_url + "/codes/room", newRoom).subscribe();
  }
}
