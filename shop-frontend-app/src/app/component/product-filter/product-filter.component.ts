import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-product-filter',
  templateUrl: './product-filter.component.html',
  styleUrls: ['./product-filter.component.scss']
})
export class ProductFilterComponent implements OnInit {

  nameFilter: string = '';

  @Output() filterApplied = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }

  applyFilter(){
    this.filterApplied.emit(this.nameFilter);
  }

}
