import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Todo} from './todo';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  private readonly TODO_URL = 'http://localhost:8000/api/todos';

  constructor(private http: HttpClient) {
  }

  create(todo: Todo): Observable<Todo> {
    return this.http.post<Todo>(this.TODO_URL, todo).pipe();
  }

  public getById(todoId: number): Observable<Todo> {
    return this.http.get<Todo>(this.TODO_URL + '/' + todoId);
  }

  public getAll(): Observable<Todo[]> {
    return this.http.get<Todo[]>(this.TODO_URL);
  }

  public update(todoDetail: Todo): Observable<any> {
    return this.http.put(this.TODO_URL + '/' + todoDetail.id, todoDetail);
  }

  public deleteById(todoId: number): Observable<any> {
    return this.http.delete(this.TODO_URL + '/' + todoId);
  }
}
