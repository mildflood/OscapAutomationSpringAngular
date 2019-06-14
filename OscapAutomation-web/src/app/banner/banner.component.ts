import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent implements OnInit {

  bannerLabel: string;
  title: string;
  


  constructor() {
    this.bannerLabel =    'OpenScap Automation Scheduler-Reporter';
    this.title = 'Openscap Automation Tool';
  }




  ngOnInit() {
  }

}
