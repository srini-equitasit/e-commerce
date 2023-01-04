import {UserDto} from "../../model/user.dto";
import {UPDATE_LOGIN_STATUS, UPDATE_LOGIN_STATUS_SUCCESS} from "../actions/user-actions";

export function userReducer(state: UserDto = {} as UserDto, action: any) {

  switch (action.type) {

    case UPDATE_LOGIN_STATUS_SUCCESS:
      return {...state, ...action.payload}
      break;

    default:
      return state;
      break;

  }
}
