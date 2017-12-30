export class Event {
    public id: string;
    public creatorName: string;
    public title: string;
    public description: string;
    public status: string;
    public town: string;
    public zipCode: number;
    public street: string;
    public block: number;
    public houseNumber: number;
    public week: number;
    public year: number;
    public duration;
    public date: string;
    public onlyUser: boolean;

    constructor(id: string, creatorName: string, title: string, description: string, status: string, town: string, zipCode: number, street: string, block: number, houseNumber: number, week: number, year: number, duration, date: string, onlyUser: boolean) {
        this.id = id;
        this.creatorName = creatorName;
        this.title = title;
        this.description = description;
        this.status = status;
        this.town = town;
        this.zipCode = zipCode;
        this.street = street;
        this.block = block;
        this.houseNumber = houseNumber;
        this.week = week;
        this.year = year;
        this.duration = duration;
        this.date = date;
        this.onlyUser = onlyUser;
    }
}