import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductListComponent } from './product-list/product-list.component';
import { SellersComponent } from './sellers/sellers.component';
import { PriceComponent } from './price/price.component';
import {RouterModule, Routes} from "@angular/router";
import {MaterialProductModule} from "../material.product.module";

const routes: Routes = [
  {
    path: '',
    component: ProductListComponent
  }
];

@NgModule({
  declarations: [
    ProductListComponent,
    SellersComponent,
    PriceComponent
  ],
  imports: [
    CommonModule,
    MaterialProductModule,
    [RouterModule.forChild(routes)]
  ]
})
export class ProductsModule { }
