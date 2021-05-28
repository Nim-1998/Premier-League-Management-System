import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SortByGoalScoreComponent } from './sort-by-goal-score.component';

describe('SortByGoalScoreComponent', () => {
  let component: SortByGoalScoreComponent;
  let fixture: ComponentFixture<SortByGoalScoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SortByGoalScoreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SortByGoalScoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
