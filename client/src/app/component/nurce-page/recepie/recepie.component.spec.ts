import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecepieComponent } from './recepie.component';

describe('RecepieComponent', () => {
  let component: RecepieComponent;
  let fixture: ComponentFixture<RecepieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecepieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecepieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
