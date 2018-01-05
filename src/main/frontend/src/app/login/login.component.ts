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
    loading = false;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private messageService: MessageService) { }

    login() {
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(
                data => {
                    this.router.navigate(['events/new']);
                },
                error => {
                    this.messageService.error(error._body);
                    this.loading = false;
                });
    }
}
