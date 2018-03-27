import {Component, OnInit} from '@angular/core';
import {Http} from "@angular/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

    constructor(private http: Http) {}

    public ngOnInit(){
        // Sets CSRF protection cookie
        this.http.get("/api/csrf/index.html").forEach(r => {});
    }
}
