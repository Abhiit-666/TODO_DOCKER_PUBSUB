import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http:HttpClient) {}

    getTask(){
      return this.http.get('http://localhost:3000/tasks');
    }

    addTask(task: any){
      return this.http.post('http://localhost:3000/tasks',task);
    }

    updateTask(task: { id: string; }){
      return this.http.put('http://localhost:3000/tasks/'+task.id,task);
    }

    deleteTask(id: string){
      return this.http.delete('http://localhost:3000/tasks/'+id);
    }

   
}
