import {Component, Input, OnInit} from '@angular/core';
import {AuthService, User} from "@auth0/auth0-angular";

@Component({
  selector: 'app-e-commerce-tb',
  templateUrl: './e-commerce-tb.component.html',
  styleUrls: ['./e-commerce-tb.component.css']
})
export class ECommerceTbComponent implements OnInit {

  @Input()
  title = '';
  authenticated: boolean = false;

  userProfile?: User | null | undefined;

  constructor(public auth: AuthService) {
  }

  ngOnInit(): void {
    this.authenticated = false;
    this.auth.isAuthenticated$.subscribe(data => {
      this.authenticated = data;
    });
    this.auth.user$.subscribe(data => {
      this.userProfile = data;
    })
  }

  logout() {
    this.auth.logout({returnTo: [document.location.origin, "home", "logout"].join("/")});
  }

  login() {
    this.auth.loginWithRedirect();
  }

}
