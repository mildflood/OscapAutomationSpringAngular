export class Formdata {
    nodename: string;
    dateOfScan: string;
    timeOfScan: string;
    group: string;
    email: string;
    constructor(nodename: string, dateOfScan: string, timeOfScan: string, group: string, email: string) {
        this.nodename = nodename;
        this.dateOfScan = dateOfScan;
        this.timeOfScan = timeOfScan;
        this.group = group;
        this.email = email;
    }
}
