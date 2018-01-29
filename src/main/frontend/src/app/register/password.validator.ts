import {Attribute, Directive, forwardRef} from '@angular/core';
import { NG_VALIDATORS,Validator, AbstractControl } from '@angular/forms';

@Directive({
    selector: '[confirmationNotEqualPassword][formControlName],[confirmationNotEqualPassword][formControl],[confirmationNotEqualPassword][ngModel]',
    providers: [
        { provide: NG_VALIDATORS, useExisting: forwardRef(() => ConfirmPasswordValidator), multi: true }
    ]
})

export class ConfirmPasswordValidator implements Validator {
    constructor(@Attribute('confirmationNotEqualPassword') public confirmationNotEqualPassword: string,
                @Attribute('reverse') public reverse: string) {}

    private get isReverse() {
        if (!this.reverse) return false;
        return this.reverse === 'true';
    }

    validate(c: AbstractControl): { [key: string]: any } {
        let v = c.value;

        let e = c.root.get(this.confirmationNotEqualPassword);

        if (e && v !== e.value && !this.isReverse) {
            return {
                confirmationNotEqualPassword: true
            }
        }

        if (e && v === e.value && this.isReverse) {
            delete e.errors['confirmationNotEqualPassword'];
            if (!Object.keys(e.errors).length) e.setErrors(null);
        }

        if (e && v !== e.value && this.isReverse) {
            e.setErrors({ confirmationNotEqualPassword: true });
        }

        return null;
    }
}