import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'sortByCreatedDate',
  pure: false
})
export class SortByCreatedDatePipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    return value.sort((a, b) => new Date(b.createdDate).getTime() - new Date(a.createdDate).getTime());
  }

}
