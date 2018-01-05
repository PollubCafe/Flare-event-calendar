export class RegisterModel {
    nick: string;
    surname: string;
    name: string;
    email: string;
    phoneNumber: string;
    password: string;
    passwordConfirm: string;

    constructor()
    constructor(nick?: string, surname?: string, name?: string, email?: string, phoneNumber?: string, password?: string, passwordConfirm?: string) {
        this.nick = nick;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}