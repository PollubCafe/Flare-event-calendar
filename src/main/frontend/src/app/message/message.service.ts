import { Injectable } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class MessageService {
    private subject = new Subject<any>();
    private keepAfterNavigationChange = false;
    private timer;

    constructor(private router: Router) {
        this.timer = Observable.timer(3000);
        router.events.subscribe(event => {
            if (event instanceof NavigationStart) {
                if (this.keepAfterNavigationChange) {
                    // only keep for a single location change
                    this.keepAfterNavigationChange = false;
                } else {
                    // clear alert
                    this.subject.next();
                }
            }
        });
    }

    success(message: string, keepAfterNavigationChange = false) {
        this.keepAfterNavigationChange = keepAfterNavigationChange;
        this.subject.next({ type: 'success', text: message });
        this.timer.subscribe(t=>this.subject.next());
    }

    error(message: string, keepAfterNavigationChange = false) {
        console.log(message);
        this.keepAfterNavigationChange = keepAfterNavigationChange;
        this.subject.next({ type: 'error', text: message });
        this.timer.subscribe(t=>this.subject.next());
    }

    getMessage(): Observable<any> {
        return this.subject.asObservable();
    }
}