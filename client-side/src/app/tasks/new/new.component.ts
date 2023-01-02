import { Component,ElementRef,OnInit,ViewChild} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {TaskService} from '../task.service';


@Component({
  selector: 'app-new',
  templateUrl: './new.component.html',
  styleUrls: ['./new.component.css']
})

export class NewComponent implements OnInit {
  @ViewChild('title') titlInput:ElementRef; 
  @ViewChild('description') descriptionInput:ElementRef;
  taskForm=FormGroup;

  constructor(private taskService:TaskService, private formBuilder:FormBuilder) { }

  ngOnInit(){

  }

  addTask(){
    const titleConteol=this.taskForm.get('title');

    this.taskService.addTask(this.taskForm.value)
    .subscribe(()=>this.taskForm.reset());
  }
}
