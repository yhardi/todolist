import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TodoListComponent } from './todo-list/todo-list.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { TodoDetailComponent } from './todo-detail/todo-detail.component';
import {FlexLayoutModule} from '@angular/flex-layout';
import { SortByCreatedDatePipe } from './sort-by-created-date.pipe';

@NgModule({
  declarations: [
    AppComponent,
    TodoListComponent,
    TodoDetailComponent,
    SortByCreatedDatePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
