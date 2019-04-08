import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import  { Observable } from 'rxjs';




@Injectable({
  providedIn: 'root'
})
export class OscapserviceService {  

  //private oscapDataUrl = 'data/dummy.json';

  /**
   * Oscap get url of oscapservice service
   */
  private oscapGetUrl = 'http://localhost:8080/ScanData/all';  

  /**
   * Oscap post url of oscapservice service
   */
  private oscapPostUrl = 'http://127.0.0.1:8080/ScanScheduler';
  
  /**
   * Author: Jonas Okwara
   * Date: 03-26-2019
   * @param httpClient 
   * declare the http client
   * of the service 
   */
  constructor(private httpClient: Http) { } 

  /**
   * Method to return the 
   * URL to the json data
   */
  getOscapData(): any {
    return this.httpClient.get(this.oscapGetUrl);
   } 
   
 postOscapData(formValues): any{
   
   return this.httpClient.post(this.oscapPostUrl, formValues);
 }  
}
