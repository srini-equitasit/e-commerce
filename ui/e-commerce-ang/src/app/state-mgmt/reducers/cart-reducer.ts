import {CartItem} from "../../model/cart-item";
import {
  ADD_CART_ITEM,
  CartAction,
  LOAD_CART_ITEMS_SUCCESS,
  REMOVE_CART_ITEM,
  UPDATE_CART_ITEM
} from "../actions/cart.action";


export function cartReducer(state: CartItem[] = [], action: any) {
  switch (action.type) {

    case LOAD_CART_ITEMS_SUCCESS:
      return [...state, ...action.payload];

    case ADD_CART_ITEM:
      return [...state, action.payload];

    case UPDATE_CART_ITEM:

      state.filter(c => c.productId === action.payload.productId).forEach(c => {
        c.qty = action.payload.qty;
        c.price = action.payload.price;

      });
      return [...state];

    case REMOVE_CART_ITEM:

      return state.filter(c => c.productId !== action.payload.productId);

    default:
      return state;
  }
}



