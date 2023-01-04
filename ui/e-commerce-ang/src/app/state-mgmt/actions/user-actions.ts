import {createAction, props} from "@ngrx/store";
import {UserDto} from "../../model/user.dto";

export const UPDATE_LOGIN_STATUS_SUCCESS = 'UPDATE_LOGIN_STATUS_SUCCESS';

export const UPDATE_LOGIN_STATUS = createAction("UPDATE_LOGIN_STATUS", props<{ payload: UserDto }>());
