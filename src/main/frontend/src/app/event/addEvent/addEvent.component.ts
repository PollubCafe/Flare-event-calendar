import {Component, OnInit, ViewChild} from "@angular/core";
import {CreatedEventRequest} from "../eventRequest.model";
import {EventService} from "../event.service";
import {SimplifiedEvent} from "../simplifiedEvent.model";
import {BsModalComponent} from "ng2-bs3-modal";
import {FormGroup} from "@angular/forms";


@Component({
    moduleId: module.id,
    selector: 'addEventButton',
    templateUrl: 'addEvent.component.html',
    styleUrls: ['addEvent.component.css']
})
export class AddEventComponent{


    @ViewChild('addEventPopup') modal: BsModalComponent;
    @ViewChild('addEventForm') form: FormGroup;

    closeModal() {
        this.form.reset();
        this.modal.dismiss();
    }

    openModal() {
        this.modal.open('lg');
    }


    createdEvent:CreatedEventRequest = new CreatedEventRequest("", "", 1, new Date(), false, "", "", "", "", "", "", "");
    currentDate: string = new Date().toJSON();

    constructor(private eventService: EventService) {
        console.log(this.currentDate);
    }

    sendEventData(){
        console.log(this.createdEvent);
        this.eventService.sendEventData(this.createdEvent).subscribe(
            (newEvent: SimplifiedEvent) => {
                this.eventService.onEventAdded.emit(newEvent);
                this.form.reset();
                this.modal.dismiss();
            }
        );
    }
}
