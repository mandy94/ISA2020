import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PacientsComponent } from './pacients.component';

describe('PacientsComponent', () => {
  let component: PacientsComponent;
  let fixture: ComponentFixture<PacientsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PacientsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PacientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
