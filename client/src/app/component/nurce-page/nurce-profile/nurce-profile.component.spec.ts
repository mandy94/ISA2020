import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NurceProfileComponent } from './nurce-profile.component';

describe('NurceProfileComponent', () => {
  let component: NurceProfileComponent;
  let fixture: ComponentFixture<NurceProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NurceProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NurceProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
