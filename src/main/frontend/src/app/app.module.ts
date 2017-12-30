import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { NavbarComponent} from "./navbar/navbar.component";
import { EventsComponent } from './event/event.component';
import { EventsListComponent } from './event/eventsList/eventsList.component';
import {EventService} from "./event/event.service";
import {AccountMenuItemComponent} from "./navbar/accountDropdown/menuItem/accountMenuItems.component";
import {NavOptionsComponent} from "./navbar/navOptions/navOptions.component";
import {SearchBarComponent} from "./navbar/searchBar/searchBar.component";
import {AccountDropdownComponent} from "./navbar/accountDropdown/accountDropdown.component";
import {PaginationComponent} from "./footer/paggination/pagination.component";
import {AddEventButtonComponent} from "./footer/addEventButton/addEventButton.component";
import {FooterComponent} from "./footer/footer.component";
import { BsDropdownModule } from 'ngx-bootstrap';

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
        AddEventButtonComponent,
        FooterComponent
    ],
    imports: [
        BsDropdownModule.forRoot(),
        BrowserModule,
        FormsModule,
        HttpModule
    ],
    providers: [EventService],
    bootstrap: [AppComponent]
})
export class AppModule { }
