export class PaginationModel {
    public fistPageNumber:number;
    public lastPageNumber:number;
    public totalPages:number;
    public currentPageNumber:number;

    constructor(totalPages:number,currentPageNumber:number) {
        this.currentPageNumber = currentPageNumber;
        this.totalPages = totalPages;
        if(totalPages>8){
            if(currentPageNumber<4){
                this.fistPageNumber = 0;
                this.lastPageNumber = 8;
            }
            else{

                if(currentPageNumber + 4 > totalPages){
                    this.fistPageNumber = totalPages - 8;
                    this.lastPageNumber = totalPages;
                }
                else{
                    this.fistPageNumber = currentPageNumber - 4;
                    this.lastPageNumber = currentPageNumber + 4;
                }
            }
        }
        else {
            this.fistPageNumber = 0;
            this.lastPageNumber = totalPages;
        }
    }
}