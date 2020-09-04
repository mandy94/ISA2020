import { Component, OnInit } from '@angular/core';
import { PrescriptionService, UserService } from 'app/service';
import { isNgTemplate } from '@angular/compiler';

@Component({
  selector: 'app-nurse',
  templateUrl: './nurse.component.html',
  styleUrls: ['./nurse.component.css']
})
export class NurseComponent implements OnInit {
  constructor(private pservice: PrescriptionService,
    private userService: UserService) { this.userService.getMyInfo(); }

  prescription_drug: any;
  getPrescrptions() {
    this.pservice.getPrescriptionByNurseId(this.userService.currentUser.id)
    .subscribe(data => this.prescription_drug = data);
  }

  signPrescription(item) {
    this.pservice.signPrescription(item.id)
      .subscribe(() => this.getPrescrptions());
    console.log(item)
  }
  ngOnInit() {
    this.userService.getMyId();

    this.getPrescrptions();
  }

}
