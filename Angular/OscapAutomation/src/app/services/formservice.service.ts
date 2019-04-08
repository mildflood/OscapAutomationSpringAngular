import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import  { Observable } from 'rxjs';
import { URLSearchParams } from '@angular/http';


@Injectable({
  providedIn: 'root'
})
export class FormService {

  private oscapPostUrl = 'http://localhost:8080/MyDemo/ScanScheduler';
  

  constructor(private httpClient: Http) { } 


  postOscapData(params: URLSearchParams) {
    return this.httpClient.post(this.oscapPostUrl, params).subscribe((data) => {});
   } 



}
