import { Injectable } from '@angular/core';
import {Headers, Http, Response} from "@angular/http";
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {
    constructor(private http: Http) { }

    login(username: string, password: string): Observable<boolean> {
        let body = `username=${username}&password=${password}`;
        let headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        return this.http.post('/api/login', body, { headers: headers })
            .map((response: Response): boolean => {
                console.log(response);
                return true;
            })
            .catch((error: any):any => {console.log(error)});
    }

    logout() {
        this.http.get('/api/logout').subscribe(
            r => {
                // Get a new CSRF token for the now anonymous session
                this.http.get("/csrf/index.html").forEach(r => {});
            },
            error => console.log(error));
    }
}