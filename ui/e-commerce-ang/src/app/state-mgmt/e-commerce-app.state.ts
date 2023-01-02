import {CartItem} from "../model/cart-item";

export interface ECommerceAppState {
  readonly cartItems: CartItem[];
}
