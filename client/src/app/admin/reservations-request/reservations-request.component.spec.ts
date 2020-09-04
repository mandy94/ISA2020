import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationsRequestComponent } from './reservations-request.component';

describe('ReservationsRequestComponent', () => {
  let component: ReservationsRequestComponent;
  let fixture: ComponentFixture<ReservationsRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReservationsRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationsRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
