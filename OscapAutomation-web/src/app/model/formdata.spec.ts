import { Formdata } from './formdata';

describe('Formdata', () => {
  it('should create an instance', () => {
    expect(new Formdata('test','test','test','test','test')).toBeTruthy();
  });
});
