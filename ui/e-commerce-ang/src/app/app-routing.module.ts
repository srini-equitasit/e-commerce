import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from '@auth0/auth0-angular';

const routes: Routes = [
  {
    path: 'products',
    canActivate: [AuthGuard],
    loadChildren: () => import('./products/products.module').then(m => m.ProductsModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./general/general.module').then(m => m.ECommerceGeneralModule)
  },

  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
