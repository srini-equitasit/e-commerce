import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {CartService} from "../../service/cart.service";
import {LOAD_CART_ITEMS, LOAD_CART_ITEMS_SUCCESS} from "../actions/cart.action";
import {catchError, debounceTime, map, mergeMap} from 'rxjs/operators';
import {EMPTY} from "rxjs";
import * as fromActions from '../actions/cart.action';

@Injectable()
export class CartEffect {


  loadCartItems$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(fromActions.LOAD_CART_ITEMS_ACTION),
      debounceTime(500),
      map(action => action.payload),
      mergeMap((userId) => this.cartService.getAll(userId)
        .pipe(
          map(cartItems => ({type: LOAD_CART_ITEMS_SUCCESS, payload: cartItems})),
          catchError(() => EMPTY)
        ))
    );
  });

  constructor(
    private actions$: Actions,
    private cartService: CartService
  ) {
  }
}

