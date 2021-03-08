package com.meowlomo.ems.security.service;

import com.meowlomo.ems.security.model.AuthenticatedUserDetails;

public interface AuthenticatedUserService {
    AuthenticatedUserDetails getAuthenticateUserDetails();

    Boolean isMeowlomoAdministrator(AuthenticatedUserDetails authenticatedUser);
}
