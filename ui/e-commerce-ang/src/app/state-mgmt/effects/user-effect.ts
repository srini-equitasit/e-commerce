import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {UserService} from "../../general/service/user.service";
import {UPDATE_LOGIN_STATUS, UPDATE_LOGIN_STATUS_SUCCESS} from "../actions/user-actions";
import {catchError, debounceTime, map, mergeMap} from "rxjs/operators";

import {EMPTY} from "rxjs";
import {UserDto} from "../../model/user.dto";

@Injectable()
export class UserEffect {

  constructor(private userService: UserService, private actions$: Actions) {
  }

  updateUserLoginStatus$ = createEffect(() => {

    return this.actions$.pipe(ofType(UPDATE_LOGIN_STATUS),
      debounceTime(500),
      map(action => action.payload),
      mergeMap(userDto => this.userService.updateLastLogin(userDto)
        .pipe(
          map(userDto => ({type: UPDATE_LOGIN_STATUS_SUCCESS, payload: userDto})),
          catchError(() => EMPTY)
        )));

  })
}
