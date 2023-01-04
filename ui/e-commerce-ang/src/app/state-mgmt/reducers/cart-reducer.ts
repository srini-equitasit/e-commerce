import {CartItem} from "../../model/cart-item";
import {
  CartAction, LOAD_CART_CNT_SUCCESS, LOAD_CART_ITEMS_STATUS_COMPLETED, LOAD_CART_ITEMS_STATUS_IN_PROGRESS,
  LOAD_CART_ITEMS_SUCCESS,
} from "../actions/cart.action";
import {CartDto} from "../../model/cart.dto";


export function cartReducer(state: CartItem[] = [], action: any) {
  switch (action.type) {

    case LOAD_CART_ITEMS_SUCCESS:
      return action.payload ? [ ...action.payload] : state;


    default:
      return state;
  }
}

export function cartCntReducer(state: CartDto, action: any) {
  switch (action.type) {

    case LOAD_CART_CNT_SUCCESS:
      return action.payload ? {...state, ...action.payload} : state;


    default:
      return state;
  }
}

export function cartStatusReducer(state: CartDto = {loadingStatus: false} as CartDto, action: any) {
  switch (action.type) {

    case LOAD_CART_ITEMS_STATUS_IN_PROGRESS:
      return action.payload ? {...state, ...action.payload} : state;

    case LOAD_CART_ITEMS_STATUS_COMPLETED:
      return action.payload ? {...state, ...action.payload} : state;


    default:
      return state;
  }
}



