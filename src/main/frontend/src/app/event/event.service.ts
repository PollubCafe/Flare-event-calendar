import {Http, Response} from "@angular/http";
import "rxjs/Rx";
import {Event} from "./event.model";
import {EventEmitter, Injectable} from "@angular/core";

@Injectable()
export class EventService {

    onEventAdded = new EventEmitter<Event>();

    constructor(private http: Http) {}

    getEventsPage(pageNumber:number){
        return this.http.get(`/api/events?pageNumber=${pageNumber}`)
            .map(
                (response: Response) => {
                    return response.json();
                }
            );
    }

}