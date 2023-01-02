import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../../products/model/product";
import {environment} from "../../../environments/environment";
import {UserDto} from "../../model/user.dto";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {

  }

  updateLastLogin(userDto: UserDto): Observable<UserDto> {

    return this.http.post<UserDto>(environment.user_url, userDto);

  }

}
