import {Component, OnInit} from '@angular/core';
import {ProductService} from "../service/product.service";
import {Product} from "../model/product";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {


  loading: boolean = false;

  errMsg = '';

  products: Product[] = []


  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.loading = true;
    this.errMsg = '';
    const obs = this.productService.getAll();
    obs.subscribe({
      next: data => {
        this.products = data;
        this.loading = false;
      },
      error: (data) => {
        this.errMsg = 'Error While loading the products';
        this.loading = false;
      }

    })
    // obs.subscribe()
  }

}
