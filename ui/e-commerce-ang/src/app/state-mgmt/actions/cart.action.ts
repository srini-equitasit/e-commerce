import {CartItem} from "../../model/cart-item";

export const ADD_CART_ITEM = 'ADD_CART_ITEM';

export const UPDATE_CART_ITEM = 'UPDATE_CART_ITEM';

export const REMOVE_CART_ITEM = 'REMOVE_CART_ITEM';

export class CartAction {

  constructor(public type: string, public payload: CartItem) {

  }
}
