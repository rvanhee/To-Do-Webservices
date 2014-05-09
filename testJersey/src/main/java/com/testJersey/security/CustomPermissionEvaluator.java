package com.testJersey.security;

import java.io.Serializable;

import javax.ws.rs.NotAuthorizedException;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class CustomPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if (targetDomainObject.toString().equals("1")) {
			return true;
		}
		
		throw new NotAuthorizedException("No Authorization");
	}

	@Override
	public boolean hasPermission(Authentication a, Serializable srlzbl, String string, Object o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}


