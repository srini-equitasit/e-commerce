import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialProductModule} from "./material.product.module";
import {HttpClientModule} from "@angular/common/http";
import {AuthModule} from '@auth0/auth0-angular';
import {ECommerceGeneralModule} from "./general/general.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialProductModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AuthModule.forRoot({
      domain: sessionStorage.getItem('authDomain') || '',
      clientId: sessionStorage.getItem('clientId') || ''
    }),
    ECommerceGeneralModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
