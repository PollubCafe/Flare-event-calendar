import {Component, OnInit} from "@angular/core";
import {AccountMenuItem} from "./menuItem/accountMenuItems.model";

@Component({
    moduleId: module.id,
    selector: 'accountDropdown',
    templateUrl: 'accountDropdown.component.html',
    styleUrls: ['./accountDropdown.component.css']
})
export class AccountDropdownComponent{

    logoutMenuItem: AccountMenuItem = new AccountMenuItem("logount","off","Logout",0);
    myProfileMenuItem: AccountMenuItem = new AccountMenuItem("myProfile","user","My profile",0);
    menuItems: AccountMenuItem[] = [
        new AccountMenuItem("myEvents","folder-open","My events",1),
        new AccountMenuItem("notConfirmedEvents","time","Not confirmed events",2),
        new AccountMenuItem("invitations","envelope","Invitations",3),
        new AccountMenuItem("myCalendar","list-alt","My calendar",4),
        new AccountMenuItem("pastEvents","backward","Past events",5)
    ];
}
