import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../model/product";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {ProductPrice} from "../model/product-price";
import {ProductSeller} from "../model/product-seller";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {

  }

  getAll(): Observable<Product[]> {

    return this.http.get<Product[]>(environment.products_url);

  }

  getPrice(productId?: number) {
    return this.http.get<ProductPrice>(environment.price_url + productId);
  }
  getSellers(productId?: number) {
    return this.http.get<ProductSeller[]>(environment.seller_url + productId);
  }
}
