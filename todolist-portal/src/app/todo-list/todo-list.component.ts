import {Component, OnInit} from '@angular/core';
import {Todo} from '../todo';
import {TodoService} from '../todo.service';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.scss']
})
export class TodoListComponent implements OnInit {
  todo: Todo = new Todo();
  todoList: Todo[] = [];
  loading: false;

  constructor(private todoService: TodoService) {
  }

  ngOnInit() {
    this.todoService.getAll().subscribe(todos => {
      this.todoList = todos;
    });
  }

  createTodo() {
    this.todoService.create(this.todo).subscribe(todo => {
      this.todoList.push(todo);
      this.todo = new Todo();
    });
  }

  delete(todoToDelete: Todo) {
    this.todoService.deleteById(todoToDelete.id).subscribe(() => {
      for (let i = 0; i < this.todoList.length; i++) {
        if (todoToDelete.id === this.todoList[i].id) {
          this.todoList.splice(i, 1);
          break;
        }
      }
    });
  }

  markAsDone(todo: Todo) {
    this.todoService.update(todo).subscribe(() => {
      console.log('Marked as done');
    });
  }
}
