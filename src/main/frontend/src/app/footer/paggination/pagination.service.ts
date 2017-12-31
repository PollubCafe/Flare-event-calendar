import { Injectable } from '@angular/core';
import { Subject }    from 'rxjs/Subject';
import {PaginationModel} from "./pagination.model";

@Injectable()
export class PaginationService {

    private currentPageNumberSource = new Subject<number>();
    private paginationDataSource = new Subject<PaginationModel>();

    currentPageNumber$ = this.currentPageNumberSource.asObservable();
    paginationData$ = this.paginationDataSource.asObservable();

    changePageNumber(pageNumber: number) {
        this.currentPageNumberSource.next(pageNumber);
    }

    changePaginationData(paginationData: PaginationModel) {
        this.paginationDataSource.next(paginationData);
    }
}