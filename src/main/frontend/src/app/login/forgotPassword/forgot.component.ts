import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {AuthenticationService} from "../../auth/auth.service";
import {MessageService} from "../../message/message.service";

@Component({
    moduleId: module.id,
    templateUrl: 'forgot.component.html',
    styleUrls: ['../login.component.css']
})

export class ForgotPasswordComponent {
    resetButtonName:string = "Reset";
    isResettingInProgress:boolean = false;
    recipientAddress:string;

    constructor(
        private router: Router,
        private authService: AuthenticationService,
        private messageService: MessageService) { }

    reset() {
        this.resetButtonName = "Resetting...";
        this.isResettingInProgress = true;
        this.authService.resetPassword(this.recipientAddress)
            .subscribe(
                data => {
                    this.messageService.success('New password is sent on your email address', true);
                    this.resetButtonName = "Reset";
                    this.isResettingInProgress = false;
                    this.router.navigate(['/login']);
                },
                error => {
                    this.messageService.error(error._body);
                    this.resetButtonName = "Reset";
                    this.isResettingInProgress = false;
                });
    }
}