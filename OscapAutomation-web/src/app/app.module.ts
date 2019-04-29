import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { MainGuiComponent } from './main-gui/main-gui.component';
import { BannerComponent } from './banner/banner.component';
import { AppformComponent } from './appform/appform.component';
import { OscapserviceService } from './services/oscapservice.service';
import { FormService } from './services/formservice.service';
import { HttpModule } from '@angular/http';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AlertComponent } from './alert/alert.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { AppRoutingModule } from './app-routing.module';
import { JwtInterceptor, ErrorInterceptor } from './helper';
import { AuthenticationService, AlertService, UserService } from './services';

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        HttpClientModule,
        ReactiveFormsModule,
        AppRoutingModule
    ],
    declarations: [
        AppComponent,
        MainGuiComponent,
        BannerComponent,
        AppformComponent,
        HomeComponent,
        LoginComponent,
        AlertComponent,
        PagenotfoundComponent
    ],

    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
        OscapserviceService,
        AuthenticationService,
        AlertService,
        UserService,
        FormService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
