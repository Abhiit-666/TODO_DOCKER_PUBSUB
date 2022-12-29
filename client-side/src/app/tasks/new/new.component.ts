import { Component } from '@angular/core';
import { FormGroup,FormControl} from '@angular/forms';
import {TaskService} from '../task.service';


@Component({
  selector: 'app-new',
  templateUrl: './new.component.html',
  styleUrls: ['./new.component.css']
})
export class NewComponent implements OnInit {

  taskForm=FormGroup;

  constructor(private taskService:TaskService) { }

  ngOnInit(){
    this.taskForm=new FormGroup({
      title:new FormControl('')
    });

  }

  addTask(){
    this.taskService.addTask(this.taskForm.value)
    .subscribe(()=>this.taskForm.reset());
  }
}