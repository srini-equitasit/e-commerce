import {Component, Input, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {ProductPrice} from "../model/product-price";

@Component({
  selector: 'app-price',
  templateUrl: './price.component.html',
  styleUrls: ['./price.component.css']
})
export class PriceComponent implements OnInit {

  @Input()
  productId?: number;

  loading = false;

  productPrice?: ProductPrice;

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.loadPrice();
  }

  loadPrice() {
    this.loading = true;
    const obs = this.productService.getPrice(this.productId);
    obs.subscribe({
      next: data => {
        this.productPrice = data;
        this.loading = false;
      },
      error: err => {
        this.loading = false;
      }
    });
  }

}
