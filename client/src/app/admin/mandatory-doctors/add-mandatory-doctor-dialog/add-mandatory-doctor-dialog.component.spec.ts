import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMandatoryDoctorDialogComponent } from './add-mandatory-doctor-dialog.component';

describe('AddMandatoryDoctorDialogComponent', () => {
  let component: AddMandatoryDoctorDialogComponent;
  let fixture: ComponentFixture<AddMandatoryDoctorDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddMandatoryDoctorDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMandatoryDoctorDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
