import { TestBed } from '@angular/core/testing';

import { OscapserviceService } from './oscapservice.service';

describe('OscapserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OscapserviceService = TestBed.get(OscapserviceService);
    expect(service).toBeTruthy();
  });
});
