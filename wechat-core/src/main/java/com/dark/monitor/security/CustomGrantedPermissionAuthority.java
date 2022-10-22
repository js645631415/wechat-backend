package com.dark.monitor.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

public class CustomGrantedPermissionAuthority implements GrantedAuthority {
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	private final String permission;

	public CustomGrantedPermissionAuthority(String permission) {
		Assert.hasText(permission, "A granted authority textual representation is required");
		this.permission = permission;
	}

	@Override
	public String getAuthority() {
		return permission;
	}

	@Override
	public int hashCode() {
		return this.permission.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof CustomGrantedPermissionAuthority) {
			return permission.equals(((CustomGrantedPermissionAuthority) obj).permission);
		}
		return false;
	}

	@Override
	public String toString() {
		return this.permission;
	}
}
