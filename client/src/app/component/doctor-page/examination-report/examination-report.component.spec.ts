import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationReportComponent } from './examination-report.component';

describe('ExaminationReportComponent', () => {
  let component: ExaminationReportComponent;
  let fixture: ComponentFixture<ExaminationReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExaminationReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExaminationReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
