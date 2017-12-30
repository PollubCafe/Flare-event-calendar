import {Component, OnInit} from "@angular/core";
import {EventService} from "../event.service";
import {Event} from "../event.model";

@Component({
    selector: 'app-tasks-add',
    templateUrl: './eventsAdd.component.html',
    styleUrls: ['./eventsAdd.component.css']
})
export class EventsAddComponent implements OnInit {

    addTaskValue: string = null;

    constructor(private taskService: EventService) {

    }
    ngOnInit() {

    }

    onTaskAdd(event) {

    }

    getTodayAsString() {
        let today = new Date();
        let dd: any = today.getDate();
        let mm: any = today.getMonth() + 1;
        let yyyy = today.getFullYear();

        if (dd < 10) {
            dd = '0' + dd;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }

        return mm + '/' + dd + '/' + yyyy;
    }
}
