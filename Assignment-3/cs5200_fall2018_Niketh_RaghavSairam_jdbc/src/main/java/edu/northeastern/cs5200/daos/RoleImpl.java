package edu.northeastern.cs5200.daos;

public interface RoleImpl {
	int assignWebsiteRole(int developerId, int websiteId, int roleId);

	int assignPageRole(int developerId, int pageId, int roleId);

	int deleteWebsiteRole(int developerId, int websiteId, int roleId);

	int deletePageRole(int developerId, int pageId, int roleId);
}
