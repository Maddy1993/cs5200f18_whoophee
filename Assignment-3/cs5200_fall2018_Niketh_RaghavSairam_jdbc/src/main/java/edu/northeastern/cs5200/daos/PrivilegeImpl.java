package edu.northeastern.cs5200.daos;

public interface PrivilegeImpl {
	int assignWebsitePrivilege(int developerId, int websiteId, String privilege);

	int assignPagePrivilege(int developerId, int pageId, String privilege);

	int deleteWebsitePrivilege(int developerId, int websiteId, String privilege);

	int deletePagePrivilege(int developerId, int pageId, String privilege);

}
