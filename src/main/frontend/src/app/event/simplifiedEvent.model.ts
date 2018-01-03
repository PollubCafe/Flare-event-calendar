import monthsNames from "../const/monthsNames";

export class SimplifiedEvent {
    public id: string;
    public title: string;
    public description: string;
    public address: string;
    public organizerName: string;
    public linksCount: number;
    public votesCount: number;
    public onlyForRegisteredUsers: boolean;
    public yearOfEventApproval: number;
    public dayOfEventApproval: number;
    public mouthOfEventApproval: string;
    public hourOfEventApproval: string;
    public duration: number;
    public imageURL: string;


    constructor();
    constructor(id: string, title: string, description: string, address: string, organizerName: string, linksCount: number, votesCount: number, onlyForRegisteredUsers: boolean, yearOfEventApproval: number,dayOfEventApproval: number,mouthOfEventApproval: string,hourOfEventApproval: string, duration: number, imageURL: string);
    constructor(id?: string, title?: string, description?: string, address?: string, organizerName?: string, linksCount?: number, votesCount?: number, onlyForRegisteredUsers?:boolean, yearOfEventApproval?: number,dayOfEventApproval?: number,mouthOfEventApproval?: string,hourOfEventApproval?: string, duration?: number, imageURL?: string) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.organizerName = organizerName;
        this.linksCount = linksCount;
        this.votesCount = votesCount;
        this.onlyForRegisteredUsers = onlyForRegisteredUsers;
        this.yearOfEventApproval = yearOfEventApproval;
        this.dayOfEventApproval = dayOfEventApproval;
        this.mouthOfEventApproval = mouthOfEventApproval;
        this.hourOfEventApproval = hourOfEventApproval;
        this.duration = duration;
        this.imageURL = imageURL;
    }
}