import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

import { User } from '@/_models';

@Injectable({ providedIn: 'root' })
export class ProductsService {

    constructor(private http: HttpClient) {
    }


    getProducts() {
        var config = {
            headers: new HttpHeaders({
                "Content-Type": "application/json"
            })
        }
        return this.http.get('http://localhost:8080/products/',config )
    }

} 