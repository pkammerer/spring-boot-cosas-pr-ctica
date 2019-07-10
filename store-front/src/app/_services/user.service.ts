    import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';

import { User } from '@/_models';

@Injectable({ providedIn: 'root' })
export class UserService {
    private accesHeader = 'Access-Control-Allow-Origin'

    constructor(private http: HttpClient) { }
    
    getAll() {
        return this.http.get<User[]>(`${config.apiUrl}/users`);
    }

    getById(id: number) {
        let header = new HttpHeaders();
        header.append('Access-Control-Allow-Origin',`*`)
        var request = new HttpRequest("GET",`http://localhost:8080/cliente/${id}`,{headers:header});
        return this.http.request(request)
    }

    register(user: User) {
        let config= {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        }   
        return this.http.post<User>(`http://localhost:8080/cliente/`,user,config)
    }

  //  update(user: User) {
    //    return this.http.put(`${config.apiUrl}/users/${user.id}`, user);
    //}

    delete(id: number) {
        return this.http.delete(`${config.apiUrl}/users/${id}`);
    }
}