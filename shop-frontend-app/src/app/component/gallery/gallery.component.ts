import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../../model/product";
import {CartService} from "../../service/cart.service";
import {AddLineItemDto} from "../../model/add-line-item-dto";

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent implements OnInit {

  @Input()products: Product[] = [];

  constructor(private cartService: CartService) { }
  constructor() { }

  ngOnInit(): void {
  }

  addToCart(id: number) {
    this.cartService.addToCart(new AddLineItemDto(id, 1,"","")).subscribe();
  }
}
