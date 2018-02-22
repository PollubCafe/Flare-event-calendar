import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule,Routes } from "@angular/router";
import { BsDropdownModule } from 'ngx-bootstrap';

import { AppComponent } from './app.component';
import { NavbarComponent} from "./navbar/navbar.component";
import { EventsComponent } from './event/event.component';
import { EventsListComponent } from './event/eventsList/eventsList.component';
import {AccountMenuItemComponent} from "./navbar/accountDropdown/menuItem/accountMenuItems.component";
import {NavOptionsComponent} from "./navbar/navOptions/navOptions.component";
import {SearchBarComponent} from "./navbar/searchBar/searchBar.component";
import {AccountDropdownComponent} from "./navbar/accountDropdown/accountDropdown.component";
import {PaginationComponent} from "./footer/paggination/pagination.component";
import {AddEventComponent} from "./event/addEvent/addEvent.component";
import {FooterComponent} from "./footer/footer.component";
import {ApprovedEventComponent} from "./approvedEvent/approvedEvent.component";
import { BsModalModule } from 'ng2-bs3-modal';
import {LoginComponent} from "./login/login.component";
import {MessageComponent} from "./message/message.component";

import {DateValidator} from "./event/addEvent/date.validator";
import {ConfirmPasswordValidator} from "./register/password.validator";

import {AuthenticationService} from "./auth/auth.service";
import {EventService} from "./event/event.service";
import {PaginationService} from "./footer/paggination/pagination.service";
import {MessageService} from "./message/message.service";
import {RegisterService} from "./register/register.service";
import {RegisterComponent} from "./register/register.component";
import {ResendVerificationEMailComponent} from "./register/resendVerficationEmail/resend.component";

const appRoutes: Routes = [
    { path: '', component: EventsComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'resendVerificationEMail', component: ResendVerificationEMailComponent },
    { path: 'login', component: LoginComponent },
    { path: 'events/new', component: EventsComponent },
    { path: 'events/approved', component: ApprovedEventComponent },
    { path: 'events/past', component: ApprovedEventComponent },
    { path: 'users', component: ApprovedEventComponent },
];

@NgModule({
    declarations: [
        AppComponent,
        EventsComponent,
        EventsListComponent,
        AccountMenuItemComponent,
        PaginationComponent,
        NavbarComponent,
        NavOptionsComponent,
        SearchBarComponent,
        AccountDropdownComponent,
        AddEventComponent,
        FooterComponent,
        ApprovedEventComponent,
        DateValidator,
        ConfirmPasswordValidator,
        MessageComponent,
        LoginComponent,
        RegisterComponent,
        ResendVerificationEMailComponent
    ],
    imports: [
        RouterModule.forRoot(appRoutes),
        BsDropdownModule.forRoot(),
        BrowserModule,
        FormsModule,
        HttpModule,
        ReactiveFormsModule,
        BsModalModule
    ],
    providers: [
        EventService,
        PaginationService,
        AuthenticationService,
        MessageService,
        RegisterService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
