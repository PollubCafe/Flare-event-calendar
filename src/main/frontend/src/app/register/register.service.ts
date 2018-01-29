import { Injectable } from '@angular/core';

import {RegisterModel} from './register.model'
import {Http} from "@angular/http";
import {CreatedEventRequest} from "../event/eventRequest.model";

@Injectable()
export class RegisterService {
    constructor(private http: Http) { }


    create(registerModel: RegisterModel) {
        return this.http.post('/api/registration', registerModel);
    }

    private completeUndefinedFieldsOfEvent(registerModel: RegisterModel){
        registerModel.phoneNumber = registerModel.phoneNumber === undefined ? "" : registerModel.phoneNumber;
    }
}