import {Component, Input, ViewChild} from "@angular/core";
import {BsModalComponent} from "ng2-bs3-modal";
import {SimplifiedEvent} from "../simplifiedEvent.model";
import {FormGroup} from "@angular/forms";


@Component({
    selector: 'showEvent',
    templateUrl: './showEvent.component.html'
})

export class SingleEventComponent{

    @Input() model: SimplifiedEvent;
    @ViewChild('showEventPopup') modal: BsModalComponent;
    @ViewChild('showEventForm') form: FormGroup;

    constructor(){
    }

    closeModal() {
        this.modal.dismiss();
    }

    openModal() {
        this.modal.open();
    }


}