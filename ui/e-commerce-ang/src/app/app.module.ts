import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialProductModule} from "./material.product.module";
import {HttpClientModule} from "@angular/common/http";
import {AuthModule} from '@auth0/auth0-angular';
import {ECommerceGeneralModule} from "./general/general.module";

import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {AuthHttpInterceptor} from '@auth0/auth0-angular';

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
      clientId: sessionStorage.getItem('clientId') || '',
      audience: sessionStorage.getItem('audienceId') || '',
      httpInterceptor: {
        allowedList: [{
          uri: "/api/*"
        }],
      },
    }),
    ECommerceGeneralModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthHttpInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
