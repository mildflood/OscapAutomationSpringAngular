import { Component, OnInit } from '@angular/core';
import { ReplaceSource } from 'webpack-sources';
import { OscapserviceService } from '../services/oscapservice.service'; 



@Component({
  selector: 'app-main-gui',
  templateUrl: './main-gui.component.html',
  styleUrls: ['./main-gui.component.css']
}) 


export class MainGuiComponent implements OnInit {
 
  nodeID: number;
  nodeName: string;
  administrator: string;
  schedDate: string;
  schedTime: string;
  status: string;
  report: string;
  oscapData: any[];
 
    
  constructor(private  oscapService: OscapserviceService) { }

   
  ngOnInit() { 
    this.oscapService.getOscapData().subscribe(response => {
      this.oscapData = response.json();
     });
  } 
  /**
   * Remove each Item in a row
   */
  removeItem(nodeID){
    const index = this.oscapData.indexOf(this.nodeID);
    this.oscapData.splice(index, 1);
  } 

  /**
   * Save each Item in a row
   * @param nodeID 
   */
  saveItem(nodeID){ 
    const index = this.oscapData.indexOf(nodeID);
    console.log("Am Saving this Item:" + index); 
  } 
  
  /**
   * Open each Item in a row
   */
  openItem(file){
    //const index = this.oscapData.indexOf(this.nodeID);
   //console.log("Am Reading this Item:" + index);   

    var rawFile = new XMLHttpRequest();
    rawFile.open("GET", file, false);
    rawFile.onreadystatechange = function ()
    {
        if(rawFile.readyState === 4)
        {
            if(rawFile.status === 200 || rawFile.status == 0)
            {
                var allText = rawFile.responseText;
                alert(allText);
            }
        }
    }
    rawFile.send(null);



  }


}
