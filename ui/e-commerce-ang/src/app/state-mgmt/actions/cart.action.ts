import {CartItem} from "../../model/cart-item";
import {createAction, props} from '@ngrx/store';

export const LOAD_CART_ITEMS_SUCCESS = 'LOAD_CART_ITEMS_SUCCESS';

export const LOAD_CART_ITEMS = 'LOAD_CART_ITEMS';

export const ADD_CART_ITEM = 'ADD_CART_ITEM';

export const UPDATE_CART_ITEM = 'UPDATE_CART_ITEM';

export const REMOVE_CART_ITEM = 'REMOVE_CART_ITEM';

export class CartAction {

  constructor(public type: string, public payload: CartItem) {

  }
}

export const LOAD_CART_ITEMS_ACTION = createAction(LOAD_CART_ITEMS, props<{ payload: number }>());


