import { SortByCreatedDatePipe } from './sort-by-created-date.pipe';

describe('SortByCreatedDatePipe', () => {
  it('create an instance', () => {
    const pipe = new SortByCreatedDatePipe();
    expect(pipe).toBeTruthy();
  });
});
