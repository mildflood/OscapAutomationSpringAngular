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
  private oscapGetUrl = 'http://localhost:8888/OscapAutomation/api/host';  

  /**
   * Oscap post url of oscapservice service
   */
  private oscapPostUrl = 'http://localhost:8888/OscapAutomation/ScanScheduler';
  
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
   
 postOscapData(formValues): Observable<any>{
   
   return this.httpClient.post(this.oscapPostUrl, formValues);
 }  
}
