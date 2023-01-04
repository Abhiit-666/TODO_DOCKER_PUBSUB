import { Component,OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { TaskService } from '../task.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  taskForm:FormGroup|undefined ;

  constructor(
    private taskService:TaskService, 
    private formBuilder:FormBuilder,
    private route:ActivatedRoute
    ) { }

  ngOnInit(){
    this.taskForm=this.formBuilder.group({
      title:['',Validators.required],
      status:[false]
    });

    const id= this.route.snapshot.paramMap.get('id');
  

  //Load the task into the form
 
  this.taskService.getTaskbyId(id).subscribe(task=>{
     if(this.taskForm){
    this.taskForm.patchValue({
      title:task.title,
      status:task.status
    });
  }
  });
}

updateTask(){
  if(this.taskForm?.valid){
    const task=this.route.snapshot.paramMap.get('id');
    this.taskService.updateTask(this.taskForm.value,task).subscribe(res=>{
      console.log(res);
    });

  }
}

}
