import {Component, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {Product} from "../model/product";
import {ProductPrice} from "../model/product-price";
import {Store} from "@ngrx/store";
import {ECommerceAppState} from "../../state-mgmt/e-commerce-app.state";

import {CartItem} from "../../model/cart-item";
import {ADD_CART_ITEMS_ACTION} from "../../state-mgmt/actions/cart.action";
import {Observable} from "rxjs";
import {UserDto} from "../../model/user.dto";
import * as fromActions from "../../state-mgmt/actions/cart.action";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {


  loading: boolean = false;

  errMsg = '';

  products: Product[] = []

  userInfo$?: Observable<UserDto>;

  userInfo?: UserDto;


  constructor(private productService: ProductService, private store: Store<ECommerceAppState>) {
  }

  ngOnInit(): void {
    this.loadProducts();
    this.loadUserInfo();
  }

  loadUserInfo() {
    this.userInfo$ = this.store.select(state => state.user);

    this.userInfo$.subscribe(data => {

      this.userInfo = data;
    });
  }

  loadProducts() {
    this.loading = true;
    this.errMsg = '';
    const obs = this.productService.getAll();
    obs.subscribe({
      next: data => {
        this.products = data;
        this.loading = false;
      },
      error: (data) => {
        this.errMsg = 'Error While loading the products';
        this.loading = false;
      }

    })
    // obs.subscribe()
  }

  addToCart(product: Product, productPrice?: ProductPrice) {
    console.log('productPrice = ' + productPrice);
    this.store.dispatch(ADD_CART_ITEMS_ACTION({
      payload: {
        userId: this.userInfo?.id,
        qty: 1,
        productId: productPrice?.productId,
        price: productPrice?.price,
      } as CartItem
    }));


  }

}
