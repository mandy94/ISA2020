import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ThrapiesComponent } from './thrapies.component';

describe('ThrapiesComponent', () => {
  let component: ThrapiesComponent;
  let fixture: ComponentFixture<ThrapiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ThrapiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ThrapiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
