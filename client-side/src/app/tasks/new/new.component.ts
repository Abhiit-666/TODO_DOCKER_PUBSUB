import { Component,ElementRef,OnInit,ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TaskService} from '../task.service';


@Component({
  selector: 'app-new',
  templateUrl: './new.component.html',
  styleUrls: ['./new.component.css']
})

export class NewComponent implements OnInit {
  
  taskForm:FormGroup | undefined;

  constructor(private taskService:TaskService, private formBuilder:FormBuilder) { }

  ngOnInit(){
    this.taskForm=this.formBuilder.group({
      title:['',Validators.required],
      status:[false]
    });

  }

  addTask(){
    if(this.taskForm?.valid){
      this.taskService.addTask(this.taskForm.value).subscribe(res=>{
        console.log(res);
      });
    }
}
}
