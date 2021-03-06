import {Component, ViewChild} from "@angular/core";
import {CreatedEventRequest} from "../eventRequest.model";
import {EventService} from "../event.service";
import {SimplifiedEvent} from "../simplifiedEvent.model";
import {BsModalComponent} from "ng2-bs3-modal";
import {FormGroup} from "@angular/forms";
import {MessageService} from "../../message/message.service";


@Component({
    selector: 'addEventButton',
    templateUrl: 'addEvent.component.html',
    styleUrls: ['addEvent.component.css']
})
export class AddEventComponent{


    @ViewChild('addEventPopup') modal: BsModalComponent;
    @ViewChild('addEventForm') form: FormGroup;
    saveButtonName: string = "Save";
    isSavingEventInProgress: boolean = false;

    closeModal() {
        this.form.reset();
        this.modal.dismiss();
    }

    openModal() {
        this.modal.open('lg');
    }


    createdEvent:CreatedEventRequest = new CreatedEventRequest();

    constructor(private eventService: EventService,private messageService: MessageService) {
    }

    sendEventData(){
        this.saveButtonName = "Saving...";
        this.isSavingEventInProgress = true;
        this.eventService.sendEventData(this.createdEvent).subscribe(
            (newEvent: SimplifiedEvent) => {
                this.isSavingEventInProgress = false;
                this.saveButtonName = "Save";
                this.eventService.onEventAdded.emit(newEvent);
                this.form.reset();
                this.modal.dismiss();
            },
            (error) => {
                this.isSavingEventInProgress = false;
                this.saveButtonName = "Save";
                this.form.reset();
                this.modal.dismiss();
                this.messageService.error(error._body);
            }
        );
    }
}
