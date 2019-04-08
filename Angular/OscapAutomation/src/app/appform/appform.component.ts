
import { Component, OnInit } from '@angular/core'; 
import { Http } from '@angular/http';
import  { Observable } from 'rxjs';
//import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { HttpClient, HttpHeaders, HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; 
import { OscapserviceService } from '../services/oscapservice.service'; 
import { FormService } from '../services/formservice.service';
import { URLSearchParams } from '@angular/http';
import { Formdata } from '../model/formdata';



interface  FormData { 
  nodename: string;
  dateOfScan: string;
  timeOfScan: string;
  group: string;
  email: string;
}


const httpOptions = {
  headers: new HttpHeaders({ 
    'Access-Control-Allow-Origin':'*',
    'Authorization':'authkey',
    'userid':'1'
  })
};


@Component({
  selector: 'app-appform',
  templateUrl: './appform.component.html',
  styleUrls: ['./appform.component.css']
})  

export class AppformComponent  { 

  /**
   * @param httpClient 
   */
  constructor(private formService:  FormService){
     
    }


 
 onSubmit(formdata){  
  const params = new URLSearchParams();
  params.set('Nodename', formdata.nodename);
  params.set('Date', formdata.dateOfScan); 
  params.set('Time', formdata.timeOfScan);
  params.set('Group', formdata.group);
  params.set('Email', formdata.email);
  console.log(formdata.message);
   this.formService.postOscapData(params);
 }

}
