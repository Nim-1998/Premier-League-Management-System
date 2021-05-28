import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SortByPointComponent } from './sort-by-point.component';

describe('SortByPointComponent', () => {
  let component: SortByPointComponent;
  let fixture: ComponentFixture<SortByPointComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SortByPointComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SortByPointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
