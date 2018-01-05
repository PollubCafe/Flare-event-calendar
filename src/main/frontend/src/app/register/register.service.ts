import { Injectable } from '@angular/core';

import {RegisterModel} from './register.model'
import {Http} from "@angular/http";

@Injectable()
export class RegisterService {
    constructor(private http: Http) { }


    create(registerModel: RegisterModel) {
        return this.http.post('/api/register', registerModel);
    }

}