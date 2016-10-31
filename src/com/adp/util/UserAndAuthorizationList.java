package com.adp.util;

import com.adp.model.User;
import com.adp.model.AuthorizationList;;

public class UserAndAuthorizationList {

		private User user;
		private AuthorizationList AuthorizationList;
		
		public UserAndAuthorizationList(User user, AuthorizationList authorizationList) {
			super();
			this.user = user;
			AuthorizationList = authorizationList;
		}
		public User getUser() {
			return user;
		}
		
		public void setUser(User user) {
			this.user = user;
		}
		public AuthorizationList getAuthorizationList() {
			return AuthorizationList;
		}
		public void setAuthorizationList(AuthorizationList authorizationList) {
			AuthorizationList = authorizationList;
		}
		
		
}
