import {Http, Response} from "@angular/http";
import "rxjs/Rx";
import {Event} from "./event.model";
import {EventEmitter, Injectable} from "@angular/core";

@Injectable()
export class EventService {

    onEventAdded = new EventEmitter<Event>();

    constructor(private http: Http) {}

    getEvents(){
        return this.http.get('/api/events')
            .map(
                (response: Response) => {
                    return response.json();
                }
            );
    }

    addEvent(event: Event) {
        return this.http.post('/api/tasks/save', event)
            .map(
                (response: Response) => {
                    return response.json();
                }
            );
    }

    saveEvent(event: Event, checked: boolean) {
        // we are updating the task to what the value of checked is
        return this.http.post('/api/tasks/save', event)
            .map(
                (response: Response) => {
                    return response.json();
                }
            );
    }

}