import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {ECommerceAppState} from "../../state-mgmt/e-commerce-app.state";
import {Observable} from "rxjs";
import {CartItem} from "../../model/cart-item";
import {LOAD_CART_ITEMS_ACTION, REMOVE_CART_ITEMS_ACTION} from "../../state-mgmt/actions/cart.action";
import {UserDto} from "../../model/user.dto";
import {MatDialog} from "@angular/material/dialog";
import {EditCartComponent} from "../edit-cart/edit-cart.component";
import {BuyCartComponent} from "../buy-cart/buy-cart.component";


@Component({
  selector: 'app-cart-items',
  templateUrl: './cart-items.component.html',
  styleUrls: ['./cart-items.component.css']
})
export class CartItemsComponent implements OnInit {

  cartItems$?: Observable<CartItem[]>;

  cartItems: CartItem[] = [];

  loading = false;

  displayedColumns = ['productId', 'qty', 'price', 'actions'];

  userInfo?: UserDto;

  constructor(private store: Store<ECommerceAppState>, public dialog: MatDialog) {
  }


  ngOnInit(): void {

    this.loadCartItems();
  }

  loadCartItems() {
    this.loading = true;

    const userInfo$ = this.store.select(state => state.user);
    userInfo$.subscribe(data => {
      this.userInfo = data;
      if (data.id) {
        this.store.dispatch(LOAD_CART_ITEMS_ACTION({payload: data.id}));
      }
    })

    this.cartItems$ = this.store.select(state => state.cartItems);
    this.cartItems$.subscribe({
      next: data => {
        this.cartItems = [...data];
        this.loading = false;
      }, error: err => {
        this.loading = false;
      }
    });
  }

  getTotalCost() {
    let totalCost = 0;
    if (this.cartItems) {
      for (const ci of this.cartItems) {
        totalCost = totalCost + (ci.price * ci.qty);
      }
    }
    return totalCost;
  }

  remove(cartItem: CartItem) {

    this.store.dispatch(REMOVE_CART_ITEMS_ACTION({payload: cartItem}));
  }

  editCart(cartItem: CartItem) {
    const dialogRef = this.dialog.open(EditCartComponent, {
      data: cartItem
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  buyCart() {
    const dialogRef = this.dialog.open(BuyCartComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

}
