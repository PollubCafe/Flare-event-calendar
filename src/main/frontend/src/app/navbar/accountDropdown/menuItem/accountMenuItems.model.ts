export class AccountMenuItem {
    public key: string;
    public iconName: string;
    public itemName: string;
    public notificationCount: number;


    constructor(key: string, iconName: string, itemName: string, notificationCount: number) {
        this.key = key;
        this.iconName = iconName;
        this.itemName = itemName;
        this.notificationCount = notificationCount;
    }
}