import { Component, OnInit } from '@angular/core';
import { ProductsService } from '@/_services/products.service';
import { Product } from '@/_models/Product';
import { ArrayType } from '@angular/compiler';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
})
export class ProductosComponent implements OnInit {

  
  constructor(private productsService: ProductsService) { }
  
  products : any 
  
  ngOnInit() {
    let cont=0
    this.productsService.getProducts().subscribe(
      data => {
        
        this.products = data
        cont++
      },
      error => {
          console.log(error)
      }
    )
    
    console.log(this.products)
  }



}
