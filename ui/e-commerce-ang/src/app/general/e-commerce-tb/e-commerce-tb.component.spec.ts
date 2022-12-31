import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ECommerceTbComponent } from './e-commerce-tb.component';

describe('ECommerceTbComponent', () => {
  let component: ECommerceTbComponent;
  let fixture: ComponentFixture<ECommerceTbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ECommerceTbComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ECommerceTbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
