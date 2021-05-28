import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SortedMatchListComponent } from './sorted-match-list.component';

describe('SortedMatchListComponent', () => {
  let component: SortedMatchListComponent;
  let fixture: ComponentFixture<SortedMatchListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SortedMatchListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SortedMatchListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
