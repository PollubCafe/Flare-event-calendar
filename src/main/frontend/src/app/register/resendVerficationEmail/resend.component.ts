import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {RegisterService} from "../register.service";
import {MessageService} from "../../message/message.service";

@Component({
    moduleId: module.id,
    templateUrl: 'resend.component.html',
    styleUrls: ['../register.component.css']
})

export class ResendVerificationEMailComponent {
    resendButtonName:string = "Resend";
    isResendingInProgress:boolean = false;
    recipientAddress:string;

    constructor(
        private router: Router,
        private registerService: RegisterService,
        private messageService: MessageService) { }

    resend() {
        this.resendButtonName = "Resending...";
        this.isResendingInProgress = true;
        this.registerService.resendVerificationMail(this.recipientAddress)
            .subscribe(
                data => {
                    this.messageService.success('E-mail resent', true);
                    this.resendButtonName = "Resend";
                    this.isResendingInProgress = false;
                    this.router.navigate(['/login']);
                },
                error => {
                    this.messageService.error(error._body);
                    this.resendButtonName = "Resend";
                    this.isResendingInProgress = false;
                });
    }
}