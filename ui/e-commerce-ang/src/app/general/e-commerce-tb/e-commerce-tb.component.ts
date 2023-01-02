import {Component, Input, OnInit} from '@angular/core';
import {AuthService, User} from "@auth0/auth0-angular";
import {UserService} from "../service/user.service";
import {UserDto} from "../../model/user.dto";
import {CartItem} from "../../model/cart-item";
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {ECommerceAppState} from "../../state-mgmt/e-commerce-app.state";
import {ADD_CART_ITEM} from "../../state-mgmt/actions/cart.action";
import * as fromActions from '../../state-mgmt/actions/cart.action';

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

  cartItems$?: Observable<CartItem[]>;

  cartItemCnt = 0;

  constructor(public auth: AuthService, private userService: UserService, private store: Store<ECommerceAppState>) {
  }

  ngOnInit(): void {

    this.authenticated = false;

    this.auth.isAuthenticated$.subscribe(data => {
      this.authenticated = data;
    });

    this.auth.user$.subscribe(data => {
      this.userProfile = data;

      this.updateLastLogin(data);
    })

    this.cartItems$ = this.store.select(state => state.cartItems);

    this.cartItems$.subscribe(data => {
      for (const ci of data) {
        this.cartItemCnt = this.cartItemCnt + ci.qty;
      }

    });

    this.store.dispatch(fromActions.LOAD_CART_ITEMS_ACTION({payload: 2}));

  }

  updateLastLogin(user: User | undefined | null) {
    if (user) {
      const userDto = {
        email: user?.email,

        fname: user?.name,

        lname: user?.family_name
      } as UserDto
      this.userService.updateLastLogin(userDto).subscribe();
    }
  }

  logout() {
    this.auth.logout({returnTo: [document.location.origin, "home", "logout"].join("/")});
  }

  login() {
    this.auth.loginWithRedirect();
  }

}
