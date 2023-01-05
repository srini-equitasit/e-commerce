import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {ECommerceAppState} from "../../state-mgmt/e-commerce-app.state";

@Component({
  selector: 'app-buy-cart',
  templateUrl: './buy-cart.component.html',
  styleUrls: ['./buy-cart.component.css']
})
export class BuyCartComponent implements OnInit {

  totalCost = 0;

  constructor(private store: Store<ECommerceAppState>) {
  }

  ngOnInit(): void {
    const cartItems$ = this.store.select(state => state.cartItems);
    cartItems$.subscribe(data => {
      for (const ci of data) {
        this.totalCost = this.totalCost + (ci.qty * ci.price);
      }
    });
  }

}
