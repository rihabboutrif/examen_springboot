import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoleComponent } from './roles.component';

describe('RolesComponent', () => {
  let component: RoleComponent;
  let fixture: ComponentFixture<RoleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
