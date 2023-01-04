import {Component, Input, OnInit} from '@angular/core';
import {AuthService, User} from "@auth0/auth0-angular";
import {UserService} from "../service/user.service";
import {UserDto} from "../../model/user.dto";
import {CartItem} from "../../model/cart-item";
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {ECommerceAppState} from "../../state-mgmt/e-commerce-app.state";

import * as fromActions from '../../state-mgmt/actions/cart.action';
import {UPDATE_LOGIN_STATUS} from "../../state-mgmt/actions/user-actions";
import {CartDto} from "../../model/cart.dto";


@Component({
  selector: 'app-e-commerce-tb',
  templateUrl: './e-commerce-tb.component.html',
  styleUrls: ['./e-commerce-tb.component.css']
})
export class ECommerceTbComponent implements OnInit {

  @Input()
  title = '';

  authenticated: boolean = false;

  cart$?: Observable<CartDto>;

  userInfo$?: Observable<UserDto>;

  userInfo?: UserDto;

  cartItemCnt = 0;

  cartLoading = false;

  constructor(public auth: AuthService, private userService: UserService, private store: Store<ECommerceAppState>) {
  }

  ngOnInit(): void {
    this.updateAuthInfo();

    this.updateCount();


  }

  updateAuthInfo() {

    this.auth.user$.subscribe(data => {
      this.updateLastLogin(data);
    })

    this.authenticated = false;

    this.userInfo$ = this.store.select(state => state.user);

    this.userInfo$.subscribe(data => {
      if (data && data.email) {
        this.authenticated = true;
        this.store.dispatch(fromActions.COUNT_CART_ITEMS_ACTION({payload: data.id}));

      } else {
        this.authenticated = false;
      }
      this.userInfo = data;
    });

  }


  updateCount() {

    this.cart$ = this.store.select(state => state.cart);

    this.cart$.subscribe({
      next: data => {
        this.cartItemCnt = data ? data.count : 0;

      },
      error: err => {

      }
    });

    let cartStatus$ = this.store.select(state => state.cartStatus);
    cartStatus$.subscribe(data => {
      if (data.loadingStatus) {
        this.cartLoading = true;
      } else {
        this.cartLoading = false;
      }
    })
  }

  updateLastLogin(user: User | undefined | null) {
    if (user) {
      const userDto = {
        email: user?.email,

        fname: user?.name,

        lname: user?.family_name
      } as UserDto

      this.store.dispatch(UPDATE_LOGIN_STATUS({payload: userDto}));

      // this.userService.updateLastLogin(userDto).subscribe();
    }
  }

  openCartItems() {

  }

  logout() {
    this.auth.logout({returnTo: [document.location.origin, "home", "logout"].join("/")});
  }

  login() {
    this.auth.loginWithRedirect();
  }

}
