import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NextVisitDialogComponent } from './next-visit-dialog.component';

describe('NextVisitDialogComponent', () => {
  let component: NextVisitDialogComponent;
  let fixture: ComponentFixture<NextVisitDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NextVisitDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NextVisitDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
