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
        return this.http.get(`/api/event/page/${pageNumber}`)
            .map(
                (response: Response) => {
                    return response.json();
                }
            );
    }

    sendEventData(createdEventRequest: CreatedEventRequest) {
        this.prepareObjectToSend(createdEventRequest);
        return this.http.post('/api/event', createdEventRequest)
            .map(
                (response: Response) => {
                    return response.json();
                }
            );
    }

    private prepareObjectToSend(createdEventRequest: CreatedEventRequest){
        createdEventRequest.description = createdEventRequest.description === undefined ?
            "" : createdEventRequest.description;
        createdEventRequest.address_blockNumber = createdEventRequest.address_blockNumber === undefined ?
            "" : createdEventRequest.address_blockNumber;
        createdEventRequest.address_additionalInformation = createdEventRequest.address_additionalInformation === undefined ?
            "" : createdEventRequest.address_additionalInformation;
    }
}