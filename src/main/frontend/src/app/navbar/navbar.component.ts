import {Component, OnInit} from "@angular/core";

@Component({
    moduleId: module.id,
    selector: 'navbar',
    templateUrl: 'navbar.component.html'
})
export class NavbarComponent{
    isIn = false;
    toggleState() {
        let bool = this.isIn;
        this.isIn = bool === false;
    }
}
