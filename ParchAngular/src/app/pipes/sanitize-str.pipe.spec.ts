import { SanitizeStrPipe } from './sanitize-str.pipe';

describe('SanitizeStrPipe', () => {
  it('create an instance', () => {
    const pipe = new SanitizeStrPipe();
    expect(pipe).toBeTruthy();
  });
});
