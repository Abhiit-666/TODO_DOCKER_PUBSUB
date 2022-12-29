import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TasksComponent } from './tasks/tasks.component';

import { NewComponent } from './tasks/new/new.component';
import { EditComponent } from './tasks/edit/edit.component';
import { DeleteComponent } from './tasks/delete/delete.component';
import { TaskComponent } from './tasks/task/task.component';
import {TaskService} from './tasks/task.service';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    TasksComponent,
    NewComponent,
    EditComponent,
    DeleteComponent,
    TaskComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot([
      { path: 'tasks', component: TasksComponent },
      { path: 'tasks/new', component: NewComponent },
      {path:'tasks/edit/:id',component:EditComponent},
      {path:'tasks/delete/:id',component:DeleteComponent},
    ])
  ],
  providers: [TaskService],
  bootstrap: [AppComponent]
})
export class AppModule { }
