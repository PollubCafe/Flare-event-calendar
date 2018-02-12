import {Component, OnInit} from "@angular/core";

import {SimplifiedEvent} from "../simplifiedEvent.model";
import {EventService} from "../event.service";
import {PaginationService} from "../../footer/paggination/pagination.service";
import {PaginationModel} from "../../footer/paggination/pagination.model";
import {MessageService} from "../../message/message.service";
import mouthNames from "../../const/monthsNames"

@Component({
    selector: 'eventsList',
    templateUrl: './eventsList.component.html',
    styleUrls: ['./eventsList.component.css']
})
export class EventsListComponent implements OnInit {

    events: SimplifiedEvent[];
    mouthNames = mouthNames;

    constructor(private eventService: EventService,
                private paginationService: PaginationService,
                private messageService: MessageService) {
        paginationService.currentPageNumber$.subscribe(
            pageNumber => {
                this.getEventsPage(pageNumber);
            },
            (error) => this.messageService.error(error._body)
        )
    }

    ngOnInit() {
        this.getEventsPage(0);

        this.eventService.onEventAdded.subscribe(
            (event: SimplifiedEvent) => this.events.push(event)
        );
    }

    getEventsPage(pageNumber){
        this.eventService.getEventsPage(pageNumber)
            .subscribe(
                (page) => {
                    this.paginationService.changePaginationData(new PaginationModel(page.totalPages,page.currentPageNumber));
                    this.events = page.content;
                },
                (error) => {
                    console.log(error);
                    this.messageService.error(error._body)
                }
            );
    }
}
