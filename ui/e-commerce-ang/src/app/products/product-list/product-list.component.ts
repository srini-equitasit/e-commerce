import {Component, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {Product} from "../model/product";
import {ProductPrice} from "../model/product-price";
import {Store} from "@ngrx/store";
import {ECommerceAppState} from "../../state-mgmt/e-commerce-app.state";
import {ADD_CART_ITEM} from "../../state-mgmt/actions/cart.action";
import {CartItem} from "../../model/cart-item";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {


  loading: boolean = false;

  errMsg = '';

  products: Product[] = []


  constructor(private productService: ProductService, private store: Store<ECommerceAppState>) {
  }

  ngOnInit(): void {
    this.loadProducts();
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
    console.log('productPrice = ' + productPrice)
    this.store.dispatch({
      type: ADD_CART_ITEM,
      payload: <CartItem>{

        userId: 1,
        qty: 1,
        productId: productPrice?.productId,
        price: productPrice?.price,
      }
    });
  }

}
