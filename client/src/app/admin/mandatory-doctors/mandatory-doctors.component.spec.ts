import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MandatoryDoctorsComponent } from './mandatory-doctors.component';

describe('MandatoryDoctorsComponent', () => {
  let component: MandatoryDoctorsComponent;
  let fixture: ComponentFixture<MandatoryDoctorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MandatoryDoctorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MandatoryDoctorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
