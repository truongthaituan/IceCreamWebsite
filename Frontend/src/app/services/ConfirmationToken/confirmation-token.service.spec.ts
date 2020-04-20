import { TestBed } from '@angular/core/testing';

import { ConfirmationTokenService } from './confirmation-token.service';

describe('ConfirmationTokenService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ConfirmationTokenService = TestBed.get(ConfirmationTokenService);
    expect(service).toBeTruthy();
  });
});
