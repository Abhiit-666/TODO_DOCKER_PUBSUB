import { Component, OnInit } from '@angular/core';
import { TaskService} from './task.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent {
  tasks:any; //array of tasks
  constructor (
    private taskService:TaskService,
    private router:Router){}

  ngOnInit(){
    this.getTasks();

  }

  addTask(){
    this.router.navigate(['tasks/new']);
  }

  editTask(task:any){
    this.router.navigate(['tasks/edit',task.id]);
  }

  getTasks(): void{
    this,this.taskService.getTask()
          .subscribe(tasks=>this.tasks=tasks);
  }
}
