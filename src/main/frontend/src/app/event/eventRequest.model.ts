export class CreatedEventRequest {
    public title: string;
    public description: string;
    public duration: number;
    public dateOfEndRegistration: Date;
    public onlyForRegisteredUsers: boolean;
    public address_town: string;
    public address_zipCode: string;
    public address_street: string;
    public address_province: string;
    public address_blockNumber: string;
    public address_houseNumber: string;
    public address_additionalInformation: string;

    constructor()
    constructor(title?: string, description?: string, duration?: number, dateOfEndRegistration?: Date, onlyForRegisteredUsers?: boolean, address_town?: string, address_zipCode?: string, address_street?: string, address_province?: string, address_blockNumber?: string, address_houseNumber?: string, address_additionalInformation?: string){
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.dateOfEndRegistration = dateOfEndRegistration;
        this.onlyForRegisteredUsers = onlyForRegisteredUsers;
        this.address_town = address_town;
        this.address_zipCode = address_zipCode;
        this.address_street = address_street;
        this.address_province = address_province;
        this.address_blockNumber = address_blockNumber;
        this.address_houseNumber = address_houseNumber;
        this.address_additionalInformation = address_additionalInformation;
    }
}