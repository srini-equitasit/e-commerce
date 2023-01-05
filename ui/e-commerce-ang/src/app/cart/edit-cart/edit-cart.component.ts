import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {CartItem} from "../../model/cart-item";
import {Store} from "@ngrx/store";
import {ECommerceAppState} from "../../state-mgmt/e-commerce-app.state";
import {ADD_CART_ITEMS_ACTION, LOAD_CART_ITEMS_ACTION} from "../../state-mgmt/actions/cart.action";


@Component({
  selector: 'app-edit-cart',
  templateUrl: './edit-cart.component.html',
  styleUrls: ['./edit-cart.component.css']
})
export class EditCartComponent implements OnInit {

  cartItem: CartItem;

  constructor(public dialogRef: MatDialogRef<EditCartComponent>,
              @Inject(MAT_DIALOG_DATA) public data: CartItem,
              private store: Store<ECommerceAppState>) {
    this.cartItem = {...this.data};
  }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onOkClick(): void {
    this.store.dispatch(ADD_CART_ITEMS_ACTION({payload: this.cartItem}));
    this.dialogRef.close();
  }

}
