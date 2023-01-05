import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {CartService} from "../../service/cart.service";
import {
  LOAD_CART_CNT_SUCCESS, LOAD_CART_ITEMS_STATUS_COMPLETED,
  LOAD_CART_ITEMS_STATUS_IN_PROGRESS,
  LOAD_CART_ITEMS_SUCCESS, UPDATE_CART_ITEMS_ACTION
} from "../actions/cart.action";
import {catchError, debounceTime, map, mergeMap, switchMap} from 'rxjs/operators';
import {EMPTY} from "rxjs";
import * as fromActions from '../actions/cart.action';
import {of, tap} from 'rxjs';
import {Store} from "@ngrx/store";
import {ECommerceAppState} from "../e-commerce-app.state";
import {CartDto} from "../../model/cart.dto";

@Injectable()
export class CartEffect {

  constructor(
    private actions$: Actions,
    private cartService: CartService,
    private store: Store<ECommerceAppState>
  ) {
  }


  loadCartItems$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(fromActions.LOAD_CART_ITEMS_ACTION),
      tap(() => this.store.dispatch({
        type: LOAD_CART_ITEMS_STATUS_IN_PROGRESS,
        payload: {loadingStatus: true} as CartDto
      })),
      debounceTime(200),
      map(action => action.payload),
      mergeMap((userId) => this.cartService.getAll(userId)
        .pipe(
          switchMap(cartItems => [{type: LOAD_CART_ITEMS_SUCCESS, payload: cartItems}, {
            type: LOAD_CART_ITEMS_STATUS_COMPLETED,
            payload: {loadingStatus: false} as CartDto
          }]),
          catchError(() => EMPTY)
        ))
    );
  });

  addCartItems$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(fromActions.ADD_CART_ITEMS_ACTION),
      tap(() => this.store.dispatch({
        type: LOAD_CART_ITEMS_STATUS_IN_PROGRESS,
        payload: {loadingStatus: true} as CartDto
      })),
      debounceTime(200),
      map(action => action.payload),
      mergeMap((userId) => this.cartService.save(userId)
        .pipe(
          switchMap(cartItem => [
            fromActions.COUNT_CART_ITEMS_ACTION({payload: cartItem[0].userId}), {
              type: LOAD_CART_ITEMS_STATUS_COMPLETED,
              payload: {loadingStatus: false} as CartDto
            }
          ]),
          catchError(() => EMPTY)
        ))
    );
  });

  updateCartItems$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(fromActions.UPDATE_CART_ITEMS_ACTION),
      tap(() => this.store.dispatch({
        type: LOAD_CART_ITEMS_STATUS_IN_PROGRESS,
        payload: {loadingStatus: true} as CartDto
      })),
      debounceTime(200),
      map(action => action.payload),
      mergeMap((userId) => this.cartService.save(userId)
        .pipe(
          switchMap(cartItem => [
            fromActions.COUNT_CART_ITEMS_ACTION({payload: cartItem[0].userId}),
            fromActions.LOAD_CART_ITEMS_ACTION({payload: cartItem[0].userId}),
            {
              type: LOAD_CART_ITEMS_STATUS_COMPLETED,
              payload: {loadingStatus: false} as CartDto
            }
          ]),
          catchError(() => EMPTY)
        ))
    );
  });

  countCartItems$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(fromActions.COUNT_CART_ITEMS_ACTION),
      tap(() => this.store.dispatch({
        type: LOAD_CART_ITEMS_STATUS_IN_PROGRESS,
        payload: {loadingStatus: true} as CartDto
      })),
      debounceTime(200),
      map(action => action.payload),
      mergeMap((userId) => this.cartService.getCount(userId)
        .pipe(
          switchMap(cart => [{
            type: LOAD_CART_CNT_SUCCESS,
            payload: cart
          }, {
            type: LOAD_CART_ITEMS_STATUS_COMPLETED,
            payload: {loadingStatus: false} as CartDto
          }]),
          catchError(() => EMPTY)
        ))
    );
  });

  removeCartItems$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(fromActions.REMOVE_CART_ITEMS_ACTION),
      tap(() => this.store.dispatch({
        type: LOAD_CART_ITEMS_STATUS_IN_PROGRESS,
        payload: {loadingStatus: true} as CartDto
      })),
      debounceTime(200),
      map(action => action.payload),
      mergeMap((ci) => this.cartService.remove(ci)
        .pipe(map(() => ci),
          switchMap(cart => [{
            type: LOAD_CART_CNT_SUCCESS,
            payload: cart
          }, fromActions.LOAD_CART_ITEMS_ACTION({payload: cart.userId}), fromActions.COUNT_CART_ITEMS_ACTION({payload: cart.userId}), {
            type: LOAD_CART_ITEMS_STATUS_COMPLETED,
            payload: {loadingStatus: false} as CartDto
          }]),
          catchError(() => EMPTY)
        ))
    );
  });

}

