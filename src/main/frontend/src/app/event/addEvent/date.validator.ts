import { Directive, forwardRef } from '@angular/core';
import { NG_VALIDATORS,Validator, AbstractControl } from '@angular/forms';

@Directive({
    selector: '[isNotPast][formControlName],[isNotPast][formControl],[isNotPast][ngModel]',
    providers: [
        { provide: NG_VALIDATORS, useExisting: forwardRef(() => DateValidator), multi: true }
    ]
})

export class DateValidator implements Validator {
    constructor() {}

    validate(c: AbstractControl): { [key: string]: any } {
        if (new Date() > new Date(c.value))
            return {
                isNotPast:true
            };

            return null
    }
}