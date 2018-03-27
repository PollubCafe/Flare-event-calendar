import { Injectable } from '@angular/core';

import {RegisterModel} from './register.model'
import {Http, Response} from "@angular/http";

@Injectable()
export class RegisterService {
    constructor(private http: Http) { }


    create(registerModel: RegisterModel) {
        this.prepareObjectToSend(registerModel);
        return this.http.post('/api/registration', registerModel).map(
            (response: Response) => {
                return response.text();
            }
        );
    }

    resendVerificationMail(recipientAddress:string){
        return this.http.post('/api/registration/token', {email:recipientAddress})
    }

    private prepareObjectToSend(registerModel: RegisterModel){
        registerModel.phoneNumber = registerModel.phoneNumber === undefined ? "" : registerModel.phoneNumber;
    }
}