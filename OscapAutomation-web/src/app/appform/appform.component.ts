
import { Component } from '@angular/core';
import { FormService } from '../services/formservice.service';
import { URLSearchParams } from '@angular/http';
import { Formdata } from '../model/formdata';

@Component({
    selector: 'app-appform',
    templateUrl: './appform.component.html',
    styleUrls: ['./appform.component.css']
})

export class AppformComponent {
    constructor(private formService: FormService) {
    }
    onSubmit(formdata: Formdata) {
        const params = new URLSearchParams();
        params.set('Nodename', formdata.nodename);
        params.set('Date', formdata.dateOfScan);
        params.set('Time', formdata.timeOfScan);
        params.set('Group', formdata.group);
        params.set('Email', formdata.email);
        console.log(formdata.nodename);
        console.log(formdata.dateOfScan);
        console.log(formdata.email);
        this.formService.postOscapData(formdata);
    }

}
