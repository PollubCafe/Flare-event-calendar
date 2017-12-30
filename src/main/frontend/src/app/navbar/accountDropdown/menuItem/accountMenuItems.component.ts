import {Component,Input} from "@angular/core";
import {AccountMenuItem} from "./accountMenuItems.model";

@Component({
    moduleId: module.id,
    selector: 'accountMenuItem',
    templateUrl: 'accountMenuItems.component.html'
})
export class AccountMenuItemComponent{
    @Input() itemContent: AccountMenuItem;
}