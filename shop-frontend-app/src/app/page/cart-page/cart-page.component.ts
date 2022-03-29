import { Component, OnInit } from '@angular/core';
import {AllCartDto} from "../../model/all-cart-dto";
import {CartService} from "../../service/cart.service";
import {LineItem} from "../../model/line-item";

export const CART_URL = 'cart'

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.scss']
})
export class CartPageComponent implements OnInit {

  content?: AllCartDto;

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.cartUpdated();
  }

  cartUpdated() {
    this.cartService.findAll().subscribe(
      res => {
        this.content = res;
      });
  }
}
