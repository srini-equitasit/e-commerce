import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../products/model/product";
import {environment} from "../../environments/environment";
import {CartItem} from "../model/cart-item";
import {of} from 'rxjs';
import {CartDto} from "../model/cart.dto";


@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) {

  }

  getAll(userId: number) {

    return this.http.get<CartItem[]>(environment.cart_items_url + `/${userId}`);
  }

  getCount(userId: number) {

    return this.http.get<CartDto>(environment.cart_items_url + `/${userId}/count`);
  }

  save(cartItem: CartItem) {
    return this.http.post<CartItem[]>(environment.cart_items_url + `/${cartItem.userId}`, cartItem);
  }

  remove(cartItem: CartItem) {

    return this.http.delete(environment.cart_items_url, {body: cartItem});
  }
}
