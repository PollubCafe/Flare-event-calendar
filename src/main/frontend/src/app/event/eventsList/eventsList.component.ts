import {Component, OnInit} from "@angular/core";
import {Response} from "@angular/http";

import {Event} from "../event.model";
import {EventService} from "../event.service";

@Component({
    selector: 'app-tasks-list',
    templateUrl: './eventsList.component.html',
    styleUrls: ['./eventsLlist.component.css']
})
export class EventsListComponent implements OnInit {

    events: Event[] = [];

    constructor(private taskService: EventService) {

    }

    ngOnInit() {
        // initial load of data
        this.taskService.getEvents()
            .subscribe(
                (events: any[]) => {
                    this.events = events
                },
                (error) => console.log(error)
            );
        // get notified when a new task has been added
        this.taskService.onEventAdded.subscribe(
            (event: Event) => this.events.push(event)
        );
    }

    getDueDateLabel(task: Event){
      return task.onlyUser ? 'label-success' : 'label-primary';
    }

    onTaskChange(event, task) {
        this.taskService.saveEvent(task,event.target.checked).subscribe();
    }
}
