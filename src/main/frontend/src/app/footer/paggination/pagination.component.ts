import {Component} from "@angular/core";
import {PaginationService} from "./pagination.service";
import {PaginationModel} from "./pagination.model";
import {PageNumberModel} from "./pageNumber.model";

@Component({
    moduleId: module.id,
    selector: 'pagination',
    templateUrl: 'pagination.component.html',
    styleUrls: ['pagination.component.css']
})
export class PaginationComponent{
    paginationData:PaginationModel;

    constructor(private paginationService: PaginationService) {
        paginationService.paginationData$.subscribe(
            paginationData => {
                this.paginationData = paginationData;
            },
            (error) => console.log(error)
        )
    }

    changePage(pageNumber){
        this.paginationService.changePageNumber(pageNumber);
    }

    createPageNumbers(firstPageNumber, lastPageNumber){
        let pageNumbers: PageNumberModel[] = [];
        for(let i = firstPageNumber; i < lastPageNumber; i++){
            pageNumbers.push(new PageNumberModel(i));
        }
        return pageNumbers;
    }
}
