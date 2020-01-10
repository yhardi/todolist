import {Component, OnInit} from '@angular/core';
import {TodoService} from '../todo.service';
import {Todo} from '../todo';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-todo-detail',
  templateUrl: './todo-detail.component.html',
  styleUrls: ['./todo-detail.component.scss']
})
export class TodoDetailComponent implements OnInit {
  todoId: number;

  todoForm = this.fb.group({
    label: ['', Validators.required],
    done: [false, Validators.required]
  });

  constructor(private todoService: TodoService, private route: ActivatedRoute, private router: Router, private fb: FormBuilder) {
  }

  ngOnInit() {
    const todoIdString = this.route.snapshot.paramMap.get('id');
    this.todoId = Number(todoIdString);
    this.todoService.getById(this.todoId).subscribe(todo => {
      this.todoForm.get('label').setValue(todo.label);
      this.todoForm.get('done').setValue(todo.done);
      this.todoForm.get('id').setValue(todo.id);
    });
  }

  save() {
    const todoDetails: Todo = {
      label: this.todoForm.get('label').value,
      done: this.todoForm.get('done').value,
      id: this.todoId
    };
    this.todoService.update(todoDetails).subscribe(todo => {
      console.log('saved with success');
      this.goBack();
    });
  }

  delete() {
    this.todoService.deleteById(this.todoId).subscribe(() => {
      this.goBack();
    });
  }

  private goBack() {
    this.router.navigate(['/todos']);
  }
}
