import {Component, OnInit} from '@angular/core';
import {TodoService} from '../todo.service';
import {Todo} from '../todo';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-todo-detail',
  templateUrl: './todo-detail.component.html',
  styleUrls: ['./todo-detail.component.scss']
})
export class TodoDetailComponent implements OnInit {
  todoDetails: Todo = new Todo();

  constructor(private todoService: TodoService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    const todoIdString = this.route.snapshot.paramMap.get('id');
    const todoId = Number(todoIdString);
    this.todoService.getById(todoId).subscribe(todo => {
      this.todoDetails = todo;
    });
  }

  save() {
    this.todoService.update(this.todoDetails).subscribe(todo => {
      console.log('saved with success');
      this.goBack();
    });
  }

  delete() {
    this.todoService.deleteById(this.todoDetails.id).subscribe(() => {
      this.goBack();
    });
  }

  private goBack() {
    this.router.navigate(['/todos']);
  }
}
