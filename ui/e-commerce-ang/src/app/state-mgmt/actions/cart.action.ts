import {CartItem} from "../../model/cart-item";
import {createAction, props} from '@ngrx/store';

export const LOAD_CART_ITEMS_SUCCESS = 'LOAD_CART_ITEMS_SUCCESS';

export const LOAD_CART_CNT_SUCCESS = 'LOAD_CART_CNT_SUCCESS';

export const LOAD_CART_ITEMS_STATUS_IN_PROGRESS = 'LOAD_CART_ITEMS_STATUS_IN_PROGRESS';

export const LOAD_CART_ITEMS_STATUS_COMPLETED = 'LOAD_CART_ITEMS_STATUS_COMPLETED';


export class CartAction {

  constructor(public type: string, public payload: CartItem) {

  }
}

export const LOAD_CART_ITEMS_ACTION = createAction('LOAD_CART_ITEMS', props<{ payload: number }>());

export const ADD_CART_ITEMS_ACTION = createAction('ADD_CART_ITEM', props<{ payload: CartItem }>());

export const UPDATE_CART_ITEMS_ACTION = createAction('ADD_CART_ITEM', props<{ payload: CartItem }>());

export const COUNT_CART_ITEMS_ACTION = createAction('COUNT_CART_ITEMS', props<{ payload: number }>());

export const REMOVE_CART_ITEMS_ACTION = createAction('REMOVE_CART_ITEM', props<{ payload: CartItem }>());





