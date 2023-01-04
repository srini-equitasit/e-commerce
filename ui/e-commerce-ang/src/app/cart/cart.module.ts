import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CartItemsComponent} from './cart-items/cart-items.component';
import {RouterModule, Routes} from "@angular/router";
import {AuthGuard} from "@auth0/auth0-angular";
import {HomeComponent} from "../general/home/home.component";
import {LogoutComponent} from "../general/logout/logout.component";
import {MaterialProductModule} from "../material.product.module";

const routes: Routes = [
  {
    path: '',
    // Protect a route by registering the auth guard in the `canActivate` hook
    canActivate: [AuthGuard],
    component: CartItemsComponent
  }
];


@NgModule({
  declarations: [
    CartItemsComponent
  ],
  imports: [
    CommonModule,
    MaterialProductModule,
    [RouterModule.forChild(routes)]
  ]
})
export class CartModule {
}
