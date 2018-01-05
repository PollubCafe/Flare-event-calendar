import {Attribute, Directive, forwardRef} from '@angular/core';
import { NG_VALIDATORS,Validator, AbstractControl } from '@angular/forms';

@Directive({
    selector: '[confirmationEqualPassword][formControlName],[confirmationEqualPassword][formControl],[confirmationEqualPassword][ngModel]',
    providers: [
        { provide: NG_VALIDATORS, useExisting: forwardRef(() => ConfirmPasswordValidator), multi: true }
    ]
})

export class ConfirmPasswordValidator implements Validator {
    constructor(@Attribute('confirmationEqualPassword') public confirmationEqualPassword: string,
                @Attribute('reverse') public reverse: string) {}

    private get isReverse() {
        if (!this.reverse) return false;
        return this.reverse === 'true';
    }

    validate(c: AbstractControl): { [key: string]: any } {
        let v = c.value;

        let e = c.root.get(this.confirmationEqualPassword);

        if (e && v !== e.value && !this.isReverse) {
            return {
                confirmationEqualPassword: false
            }
        }

        if (e && v === e.value && this.isReverse) {
            delete e.errors['confirmationEqualPassword'];
            if (!Object.keys(e.errors).length) e.setErrors(null);
        }

        if (e && v !== e.value && this.isReverse) {
            e.setErrors({ confirmationEqualPassword: false });
        }

        return null;
    }
}