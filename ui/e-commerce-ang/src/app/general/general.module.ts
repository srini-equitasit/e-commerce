import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import {RouterModule, Routes} from "@angular/router";
import { AuthGuard } from '@auth0/auth0-angular';
import { ECommerceTbComponent } from './e-commerce-tb/e-commerce-tb.component';
import {MaterialProductModule} from "../material.product.module";
import { LogoutComponent } from './logout/logout.component';


const routes: Routes = [
  {
    path: '',
    // Protect a route by registering the auth guard in the `canActivate` hook
    canActivate: [AuthGuard],
    component: HomeComponent
  },
  {
    path: 'logout',
    component: LogoutComponent
  }
];

@NgModule({
  declarations: [
    HomeComponent,
    ECommerceTbComponent,
    LogoutComponent
  ],
  exports: [
    ECommerceTbComponent
  ],
  imports: [
    CommonModule,
    MaterialProductModule,
    [RouterModule.forChild(routes)]
  ]
})
export class ECommerceGeneralModule { }
