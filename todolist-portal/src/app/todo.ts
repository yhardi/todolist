export class Todo {
  id: number;
  label: string;
  done: boolean;

  constructor(label?: string) {
    this.label = label;
  }
}
