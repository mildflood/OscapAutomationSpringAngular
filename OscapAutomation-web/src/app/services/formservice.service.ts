import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Formdata } from '../model/formdata';

@Injectable({
    providedIn: 'root'
})
export class FormService {

    private oscapPostUrl = 'http://localhost:8888/OscapAutomation/ScanScheduler';

    constructor(private httpClient: HttpClient) { }

    postOscapData(formdata: Formdata) {
        console.log(formdata.nodename);
        console.log(formdata.dateOfScan);
        console.log(formdata.timeOfScan);
        console.log(formdata.email);
        return this.httpClient.post(this.oscapPostUrl, formdata)
            .subscribe(() => { });

    }

}
