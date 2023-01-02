import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../products/model/product";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) {

  }

  getAll(userId: number) {

    return this.http.get<Product[]>(environment.cart_items_url + `/${userId}`);
  }
}
