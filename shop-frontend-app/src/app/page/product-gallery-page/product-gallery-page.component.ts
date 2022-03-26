import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../service/product.service";
import {Product} from "../../model/product";
import {Page} from "../../model/page";

@Component({
  selector: 'app-product-gallery-page',
  templateUrl: './product-gallery-page.component.html',
  styleUrls: ['./product-gallery-page.component.scss']
})
export class ProductGalleryPageComponent implements OnInit {

  products: Product[] = [];

  page?: Page;

  nameFilter?: string;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.findAll().subscribe( res => {
      console.log("Loading products");
      this.page = res;
      this.products = res.content;
    }, err => {
      console.log(`Error loading products ${err}`);
    });
  }

  goToPage(page: number) {
    this.productService.findAll(this.nameFilter, page).subscribe( res => {
      console.log("Loading products");
      this.page = res;
      this.products = res.content;
    }, err => {
      console.log(`Error loading products ${err}`);
    });

  }

  filterApplied(filter: string) {
    this.productService.findAll(filter, 1).subscribe(res => {
      this.nameFilter = filter;
      this.page = res;
      this.products = res.content;
    }, err => {
      console.log(`Error loading products ${err}`);
    });
  }
}
