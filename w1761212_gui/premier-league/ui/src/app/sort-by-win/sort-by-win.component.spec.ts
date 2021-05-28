import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SortByWinComponent } from './sort-by-win.component';

describe('SortByWinComponent', () => {
  let component: SortByWinComponent;
  let fixture: ComponentFixture<SortByWinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SortByWinComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SortByWinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
