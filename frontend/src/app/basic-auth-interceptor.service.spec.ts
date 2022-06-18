import { TestBed } from '@angular/core/testing';

import { BasicAuthInterceptorService } from './basic-auth-interceptor.service';

describe('BasicAuthInterceptorService', () => {
  let service: BasicAuthInterceptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BasicAuthInterceptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
