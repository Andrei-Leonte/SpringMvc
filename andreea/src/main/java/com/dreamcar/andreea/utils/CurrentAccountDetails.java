package com.dreamcar.andreea.utils;

import com.dreamcar.andreea.entites.User;
import com.dreamcar.andreea.entites.base.DreamcarUser;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public  class CurrentAccountDetails {

    public static User GetUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var principal = auth.getPrincipal();
		var dreamcarUser =(DreamcarUser)principal;

		return dreamcarUser.getUser();
	}
}
