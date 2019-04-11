import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainGuiComponent } from './main-gui/main-gui.component';
import { BannerComponent } from './banner/banner.component';
import { AppformComponent } from './appform/appform.component';
import { OscapserviceService } from './services/oscapservice.service'; 
import { FormService } from './services/formservice.service';
import { Http, HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    MainGuiComponent,
    BannerComponent,
    AppformComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule,
   
  ],
  providers: [
    OscapserviceService,
    FormService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
