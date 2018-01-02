import {Http, Response} from "@angular/http";
import "rxjs/Rx";
import {SimplifiedEvent} from "./simplifiedEvent.model";
import {EventEmitter, Injectable} from "@angular/core";
import {CreatedEventRequest} from "./eventRequest.model";

@Injectable()
export class EventService {

    onEventAdded = new EventEmitter<SimplifiedEvent>();

    constructor(private http: Http) {}

    getEventsPage(pageNumber:number){
        return this.http.get(`/api/events?pageNumber=${pageNumber}`)
            .map(
                (response: Response) => {
                    return response.json();
                }
            );
    }

    sendEventData(createdEventRequest: CreatedEventRequest) {
        return this.http.post('/api/events', createdEventRequest)
            .map(
                (response: Response) => {
                    return response.json();
                }
            );
    }

}