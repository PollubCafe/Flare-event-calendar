import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import {AuthenticationService} from "../auth/auth.service";
import {MessageService} from "../message/message.service";

@Component({
    moduleId: module.id,
    templateUrl: 'login.component.html',
    styleUrls: ['login.component.css']
})

export class LoginComponent{
    model: any = {};
    isLoginInProgress = false;
    loginButtonName = "Login";

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private messageService: MessageService) { }

    login() {
        this.loginButtonName = "In progress...";
        this.isLoginInProgress = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(
                data => {
                    this.loginButtonName = "Login";
                    this.isLoginInProgress = false;
                    this.router.navigate(['events/new']);
                },
                error => {
                    this.loginButtonName = "Login";
                    this.isLoginInProgress = false;
                    console.log(error);
                    this.messageService.error(error._body);
                });
    }
}
