import {Component, OnInit} from "@angular/core";

import {Event} from "../event.model";
import {EventService} from "../event.service";
import {PaginationService} from "../../footer/paggination/pagination.service";
import {PaginationModel} from "../../footer/paggination/pagination.model";

@Component({
    selector: 'eventsList',
    templateUrl: './eventsList.component.html',
    styleUrls: ['./eventsList.component.css']
})
export class EventsListComponent implements OnInit {

    events: Event[] = [];

    constructor(private eventService: EventService,private paginationService: PaginationService) {
        paginationService.currentPageNumber$.subscribe(
            pageNumber => {
                this.getEventsPage(pageNumber);
            },
            (error) => console.log(error)
        )
    }

    ngOnInit() {
        this.getEventsPage(0);

        this.eventService.onEventAdded.subscribe(
            (event: Event) => this.events.push(event)
        );
    }

    getEventsPage(pageNumber){
        this.eventService.getEventsPage(pageNumber)
            .subscribe(
                (page) => {
                    this.paginationService.changePaginationData(new PaginationModel(page.totalPages,page.currentPageNumber));
                    this.events = page.content;
                },
                (error) => console.log(error)
            );
    }
}
