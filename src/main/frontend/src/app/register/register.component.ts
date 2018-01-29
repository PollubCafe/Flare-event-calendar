import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {RegisterService} from "./register.service";
import {MessageService} from "../message/message.service";
import {RegisterModel} from "./register.model";

@Component({
    moduleId: module.id,
    templateUrl: 'register.component.html',
    styleUrls: ['register.component.css']
})

export class RegisterComponent {
    registerModel: RegisterModel = new RegisterModel();
    registerButtonName:string = "Register";
    isRegistrationInProgress:boolean = false;

    constructor(
        private router: Router,
        private registerService: RegisterService,
        private messageService: MessageService) { }

    register() {
        this.registerButtonName = "Registering...";
        this.isRegistrationInProgress = true;
        this.registerService.create(this.registerModel)
            .subscribe(
                data => {
                    this.messageService.success('Registration successful', true);
                    this.registerButtonName = "Register";
                    this.isRegistrationInProgress = false;
                    this.router.navigate(['/login']);
                },
                error => {
                    this.messageService.error(error._body);
                    this.registerButtonName = "Register";
                    this.isRegistrationInProgress = false;
                });
    }
}
