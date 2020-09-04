import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditExaminationDialogComponent } from './edit-examination-dialog.component';

describe('EditExaminationDialogComponent', () => {
  let component: EditExaminationDialogComponent;
  let fixture: ComponentFixture<EditExaminationDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditExaminationDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditExaminationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
