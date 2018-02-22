import { Injectable } from '@angular/core';

import {RegisterModel} from './register.model'
import {Http} from "@angular/http";

@Injectable()
export class RegisterService {
    constructor(private http: Http) { }


    create(registerModel: RegisterModel) {
        return this.http.post('/api/registration', this.completeUndefinedFieldsOfRegistration(registerModel));
    }

    resendVerificationMail(recipientEMail:string){
        return this.http.post('/api/resendToken', recipientEMail)
    }

    private completeUndefinedFieldsOfRegistration(registerModel: RegisterModel){
        registerModel.phoneNumber = registerModel.phoneNumber === undefined ? "" : registerModel.phoneNumber;
    }
}