import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPreviewComponent } from './search-preview.component';

describe('SearchPreviewComponent', () => {
  let component: SearchPreviewComponent;
  let fixture: ComponentFixture<SearchPreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchPreviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
