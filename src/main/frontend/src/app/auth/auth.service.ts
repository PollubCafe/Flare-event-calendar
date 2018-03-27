import { Injectable } from '@angular/core';
import {Headers, Http } from "@angular/http";
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {
    constructor(private http: Http) { }

    login(username: string, password: string){
        let body = `username=${username}&password=${password}`;
        console.log(body);
        let headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        return this.http.post('/api/login', body, { headers: headers });
    }

    logout() {
        this.http.get('/api/logout').subscribe(
            r => {
                // Get a new CSRF token for the now anonymous session
                this.http.get("/api/csrf/index.html").forEach(r => {});
            },
            error => console.log(error));
    }

    resetPassword(recipientAddress: string) {
        return this.http.post('/api/forgotten_password',{email:recipientAddress});
    }
}