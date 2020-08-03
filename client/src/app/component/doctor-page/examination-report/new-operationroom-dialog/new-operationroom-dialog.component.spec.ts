import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewOperationroomDialogComponent } from './new-operationroom-dialog.component';

describe('NewOperationroomDialogComponent', () => {
  let component: NewOperationroomDialogComponent;
  let fixture: ComponentFixture<NewOperationroomDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewOperationroomDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewOperationroomDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
