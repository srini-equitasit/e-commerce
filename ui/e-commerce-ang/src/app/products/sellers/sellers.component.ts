import {Component, Input, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {ProductSeller} from "../model/product-seller";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-sellers',
  templateUrl: './sellers.component.html',
  styleUrls: ['./sellers.component.css']
})
export class SellersComponent implements OnInit {

  sellers?: ProductSeller[];

  loading = false;

  @Input()
  productId?: number;

  showDelay = new FormControl(1000);
  hideDelay = new FormControl(2000);

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.loadingSellers();
  }

  loadingSellers() {
    this.loading = true;
    const obs = this.productService.getSellers(this.productId);
    obs.subscribe({
      next: data => {
        this.sellers = data;
        this.loading = false;
      },
      error: err => {
        this.loading = false;
      }
    })
  }

  getSellerNames() {
    if (this.sellers) {
      return this.sellers.map(s => s.name).join(" , ")
    } else {
      return '';
    }
  }

}
