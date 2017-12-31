import {Component, OnInit} from "@angular/core";
import { Location } from '@angular/common';

@Component({
    moduleId: module.id,
    selector: 'navOptions',
    templateUrl: 'navOptions.component.html'
})
export class NavOptionsComponent{
    location:Location;
    constructor(location: Location) {
        this.location = location;
    }
}
