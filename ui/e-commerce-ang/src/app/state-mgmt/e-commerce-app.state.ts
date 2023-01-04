import {CartItem} from "../model/cart-item";
import {UserDto} from "../model/user.dto";
import {CartDto} from "../model/cart.dto";

export interface ECommerceAppState {
  readonly cartItems: CartItem[];
  readonly user: UserDto;
  readonly cart: CartDto;
  readonly cartStatus: CartDto;
}
