package edu.northeastern.cs5200;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.models.*;

public class hw_jdbc_last_first {
	private Collection<Role> defaultRoles = null;
	private HashMap<String, Integer> roleNameMapping = null;

	private void setDefaultRoles() {
		this.defaultRoles = new ArrayList<Role>();
		this.roleNameMapping = new HashMap<>();

		this.defaultRoles.add(new Role(1, "owner"));
		this.defaultRoles.add(new Role(2, "admin"));
		this.defaultRoles.add(new Role(3, "writer"));
		this.defaultRoles.add(new Role(4, "editor"));
		this.defaultRoles.add(new Role(5, "reviewer"));

		for (Role role : this.defaultRoles) {
			this.roleNameMapping.put(role.getRole(), role.getId());
		}

	}

	public int findRoleId(String rolename) {
		return this.roleNameMapping.get(rolename);
	}

	public int findIdofDeveloper(String name) {
		int result = 0;
		DeveloperDao developerdao = DeveloperDao.getInstance();
		Collection<Developer> developers = developerdao.findAllDevelopers();

		for (Developer developer : developers) {
			String username = developer.getUsername();
			if (username.equals(name)) {
				result = developer.getId();
				break;
			}
		}
		developers.clear();
		return result;
	}

	public int findIdofWebsite(String name) {
		int result = 0;
		WebsiteDao websitedao = WebsiteDao.getInstance();
		Collection<Website> websites = websitedao.findAllWebsites();
		for (Website website : websites) {
			if (website.getName().contains(name)) {
				result = website.getId();
				break;
			}
		}
		websites.clear();
		return result;
	}

	public int findIdofPageContains(String title) {
		int result = 0;
		PageDao pagedao = PageDao.getInstance();
		Collection<Page> pages = pagedao.findAllPages();
		for (Page page : pages) {
			if (page.getTitle().contains(title)) {
				result = page.getId();
				break;
			}
		}
		pages.clear();
		return result;
	}

	public int findIdofWidget(String name) {
		int result = 0;
		WidgetDao widgetdao = WidgetDao.getInstance();
		Collection<Widget> widgets = widgetdao.findAllWidgets();
		for (Widget widget : widgets) {
			if (widget.getName().equals(name)) {
				result = widget.getId();
				break;
			}
		}
		widgets.clear();
		return result;
	}

	public void createDeveloperUser() {
		DeveloperDao developerdao = DeveloperDao.getInstance();
		UserDao userdao = UserDao.getInstance();

		Collection<Phone> phones = new ArrayList<Phone>();
		Phone phone1 = new Phone("6173720940", true);
		phones.add(phone1);
		List<Address> addresses = new ArrayList<Address>();
		Address address1 = new Address("115 Forsyth", "Shillman", "Boston", "MA", "02115", true);
		addresses.add(address1);

		Developer developer1 = new Developer(12, "4321rewq", "Alice", "Wonder", "alice", "alice", "alice@wonder.com",
				null, phones, addresses);
		Developer developer2 = new Developer(23, "5432trew", "Bob", "Marley", "bob", "bob", "bob@marley.com", null,
				phones, addresses);
		Developer developer3 = new Developer(34, "6543ytre", "Charles", "Garcia", "charlie", "charlie",
				"chuch@garcia.com", null, phones, addresses);
		developerdao.createDeveloper(developer1);
		developerdao.createDeveloper(developer2);
		developerdao.createDeveloper(developer3);

		User user1 = new User(45, "7654fda", "Dan", "Martin", "dan", "dan", "dan@martin.com", null, phones, addresses);
		User user2 = new User(56, "5678dfgh", "Ed", "Karaz", "ed", "ed", "ed@kar.com", null, phones, addresses);
		userdao.createUser(user1);
		userdao.createUser(user2);
	}

	public void createWebsite() {
		WebsiteDao websitedao = WebsiteDao.getInstance();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		Website website1 = new Website(123, "Facebook", "an online social media and social networking service", date,
				date, 1234234);
		Website website2 = new Website(234, "Twitter", "an online news and social networking service", date, date,
				4321543);
		Website website3 = new Website(345, "Wikipedia", "a free online encyclopedia", date, date, 3456654);
		Website website4 = new Website(456, "CNN", "an American basic cable and satellite television news channel",
				date, date, 6543345);
		Website website5 = new Website(567, "CNET",
				"an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics",
				date, date, 5433455);
		Website website6 = new Website(678, "Gizmodo",
				"a design, technology, science and science fiction website that also writes articles on politics", date,
				date, 4322345);

		int aliceId = findIdofDeveloper("alice");
		int bobId = findIdofDeveloper("bob");
		int charlieId = findIdofDeveloper("charlie");

		websitedao.createWebsiteForDeveloper(aliceId, website1);
		websitedao.createWebsiteForDeveloper(bobId, website2);
		websitedao.createWebsiteForDeveloper(charlieId, website3);
		websitedao.createWebsiteForDeveloper(aliceId, website4);
		websitedao.createWebsiteForDeveloper(bobId, website5);
		websitedao.createWebsiteForDeveloper(charlieId, website6);

		RoleDao roledao = RoleDao.getInstance();
		roledao.setRolesDesigned(this.defaultRoles);
		int ownerId = findRoleId("owner");
		int editorId = findRoleId("editor");
		int adminId = findRoleId("admin");

		roledao.assignWebsiteRole(aliceId, 123, ownerId);
		roledao.assignWebsiteRole(bobId, 123, editorId);
		roledao.assignWebsiteRole(charlieId, 123, adminId);

		roledao.assignWebsiteRole(bobId, 234, ownerId);
		roledao.assignWebsiteRole(charlieId, 234, editorId);
		roledao.assignWebsiteRole(aliceId, 234, adminId);

		roledao.assignWebsiteRole(charlieId, 345, ownerId);
		roledao.assignWebsiteRole(aliceId, 345, editorId);
		roledao.assignWebsiteRole(bobId, 345, adminId);

		roledao.assignWebsiteRole(aliceId, 456, ownerId);
		roledao.assignWebsiteRole(bobId, 456, editorId);
		roledao.assignWebsiteRole(charlieId, 456, adminId);

		roledao.assignWebsiteRole(bobId, 567, ownerId);
		roledao.assignWebsiteRole(charlieId, 567, editorId);
		roledao.assignWebsiteRole(aliceId, 567, adminId);

		roledao.assignWebsiteRole(charlieId, 678, ownerId);
		roledao.assignWebsiteRole(aliceId, 678, editorId);
		roledao.assignWebsiteRole(bobId, 678, adminId);

	}

	public void createPage() {
		PageDao pagedao = PageDao.getInstance();
		Date date_created = Date.valueOf("2018-09-05");
		Date date_updated = Date.valueOf("2018-10-31");

		Page page1 = new Page(123, "Home", "Landing page", date_created, date_updated, 123434);
		Page page2 = new Page(234, "About", "Website description", date_created, date_updated, 234545);
		Page page3 = new Page(345, "Contact", "Addresses,phones,and contact info", date_created, date_updated, 345656);
		Page page4 = new Page(456, "Perferences", "Where users can configure their preferences", date_created,
				date_updated, 456776);
		Page page5 = new Page(567, "Profile", "Users can configure their personal information", date_created,
				date_updated, 567878);

		pagedao.createPageForWebsite(findIdofWebsite("CNET"), page1);
		pagedao.createPageForWebsite(findIdofWebsite("Gizmodo"), page2);
		pagedao.createPageForWebsite(findIdofWebsite("Wikipedia"), page3);
		pagedao.createPageForWebsite(findIdofWebsite("CNN"), page4);
		pagedao.createPageForWebsite(findIdofWebsite("CNET"), page5);

		RoleDao roledao = RoleDao.getInstance();
		roledao.setRolesDesigned(this.defaultRoles);
		int editorId = findRoleId("editor");
		int reviewerId = findRoleId("reviewer");
		int writerId = findRoleId("writer");

		int aliceId = findIdofDeveloper("alice");
		int bobId = findIdofDeveloper("bob");
		int charlieId = findIdofDeveloper("charlie");

		roledao.assignPageRole(aliceId, 123, editorId);
		roledao.assignPageRole(bobId, 123, reviewerId);
		roledao.assignPageRole(charlieId, 123, writerId);

		roledao.assignPageRole(bobId, 234, editorId);
		roledao.assignPageRole(charlieId, 234, reviewerId);
		roledao.assignPageRole(aliceId, 234, writerId);

		roledao.assignPageRole(charlieId, 345, editorId);
		roledao.assignPageRole(aliceId, 345, reviewerId);
		roledao.assignPageRole(bobId, 345, writerId);

		roledao.assignPageRole(aliceId, 456, editorId);
		roledao.assignPageRole(bobId, 456, reviewerId);
		roledao.assignPageRole(charlieId, 456, writerId);

		roledao.assignPageRole(bobId, 567, editorId);
		roledao.assignPageRole(charlieId, 567, reviewerId);
		roledao.assignPageRole(aliceId, 567, writerId);

	}

	public void createWidget() {
		WidgetDao widgetdao = WidgetDao.getInstance();

		HeadingWidget widget1 = new HeadingWidget("head123", null, null, "Welcome", 0);
		HtmlWidget widget2 = new HtmlWidget("post234", null, null, "<p>Lorem</p>", 0);
		HeadingWidget widget3 = new HeadingWidget("head345", null, null, "Hi", 1);
		HtmlWidget widget4 = new HtmlWidget("intro456", null, null, "<h1>Hi</h1>", 2);

		ImageWidget widget5 = new ImageWidget("image345", 50, 100, null, null, null, 3, "/img/567.png");
		YouTubeWidget widget6 = new YouTubeWidget("video456", 400, 300, null, null, null, 0,
				"https://youtu.be/h67VX51QXiQ", false, false);

		widgetdao.createWidgetForPage(findIdofPageContains("Home"), widget1);
		widgetdao.createWidgetForPage(findIdofPageContains("About"), widget2);
		widgetdao.createWidgetForPage(findIdofPageContains("Contact"), widget3);
		widgetdao.createWidgetForPage(findIdofPageContains("Contact"), widget4);
		widgetdao.createWidgetForPage(findIdofPageContains("Contact"), widget5);
		widgetdao.createWidgetForPage(findIdofPageContains("Perferences"), widget6);
	}

	public void updateDeveloper(String name, Boolean primary, String phone_number) {
		DeveloperDao developerdao = DeveloperDao.getInstance();
		Developer developer = developerdao.findDeveloperByUsername(name);
		Developer developer_full = developerdao.findDeveloperById(developer.getId());
		Collection<Phone> phones = developer_full.getPhones();
		Collection<Phone> phonesNew = new ArrayList<Phone>();

		for (Phone phone : phones) {
			if (phone.getPrimary() == primary)
				phone.setPhone(phone_number);
			phonesNew.add(phone);
		}
		developer_full.setPhones(phonesNew);
		developerdao.updateDeveloper(developer_full.getId(), developer_full);
	}

	public void updateWidget(String name, int orderNew) {
		int widgetId = findIdofWidget(name);
		WidgetDao widgetdao = WidgetDao.getInstance();
		Widget widget = widgetdao.findWidgetById(widgetId);
		int orderOld = widget.getOrder();
		int orderDifference = orderNew - orderOld;
		Collection<Widget> widgets = widgetdao.findAllWidgets();
		int orderTotal = 0;
		for (Widget widgetTemp : widgets) {
			if (widgetTemp.getOrder() != 0)
				orderTotal++;
		}
		for (Widget widgetTemp : widgets) {
			if (widgetTemp.getOrder() != 0) {
				int orderNewTemp = (widgetTemp.getOrder() + orderDifference) % orderTotal;
				if (orderNewTemp == 0)
					widgetTemp.setOrder(orderTotal);
				else
					widgetTemp.setOrder(orderNewTemp);
				widgetdao.updateWidget(widgetTemp.getId(), widgetTemp);
			}

		}

	}

	public void updatePage(String appendString, String name) {
		int webId = findIdofWebsite(name);
		PageDao pagedao = PageDao.getInstance();
		Collection<Page> pages = pagedao.findPagesForWebsite(webId);
		for (Page page : pages) {
			page.setTitle(appendString + page.getTitle());
			pagedao.updatePage(page.getId(), page);
		}
	}

	public void updateRoles(String webName, String pageTitle, String name1, String name2) {
		RoleDao roledao = RoleDao.getInstance();
		roledao.setRolesDesigned(this.defaultRoles);

		int pageId = findIdofPageContains(pageTitle);
		int developerId1 = findIdofDeveloper(name1);
		int developerId2 = findIdofDeveloper(name2);

		Role role1 = roledao.findPageroleById(developerId1, pageId);
		Role role2 = roledao.findPageroleById(developerId2, pageId);

		int Idrole1 = findRoleId(role1.getRole());
		int Idrole2 = findRoleId(role2.getRole());

		roledao.deletePageRole(developerId1, pageId, Idrole1);
		roledao.deletePageRole(developerId2, pageId, Idrole2);

		roledao.assignPageRole(developerId1, pageId, Idrole2);
		roledao.assignPageRole(developerId2, pageId, Idrole1);
	}

	public void deleteAddress(String name, Boolean primary) {
		DeveloperDao developerdao = DeveloperDao.getInstance();
		int developerId = findIdofDeveloper(name);
		developerdao.deleteAddressofDeveloper(developerId, primary);

	}

	public void deleteWidget(String pageName) {
		int pageId = findIdofPageContains(pageName);
		WidgetDao widgetdao = WidgetDao.getInstance();
		Collection<Widget> widgets = widgetdao.findWidgetsForPage(pageId);

		ArrayList<Integer> arrli = new ArrayList<Integer>();
		for (Widget widget : widgets) {
			arrli.add(widget.getOrder());
		}
		int orderMax = Collections.max(arrli);
		for (Widget widget : widgets) {
			if (widget.getOrder() == orderMax)
				widgetdao.deleteWidget(widget.getId());
		}

	}

	public void deletePage(String webName) {
		int webId = findIdofWebsite(webName);
		PageDao pagedao = PageDao.getInstance();
		Collection<Page> pages = pagedao.findPagesForWebsite(webId);
		Collection<Date> dates = new ArrayList<Date>();
		for (Page page : pages) {
			dates.add(page.getUpdated());
		}
		for (Page page : pages) {
			if (page.getUpdated().equals(Collections.max(dates)))
				pagedao.deletePage(page.getId());
		}
	}

	public void deleteWebsite(String name) {
		int webId = findIdofWebsite(name);
		WebsiteDao websitedao = WebsiteDao.getInstance();
		websitedao.findWebsiteById(webId);

		websitedao.deleteWebsite(webId);
		PageDao pagedao = PageDao.getInstance();
		Collection<Page> pages = pagedao.findPagesForWebsite(webId);
		for (Page page : pages)
			pagedao.deletePage(page.getId());
	}

	public static void main(String arg[]) {

		hw_jdbc_last_first demoInstance = new hw_jdbc_last_first();
		demoInstance.setDefaultRoles();

		System.out.println("Begin Inserts");
		demoInstance.createDeveloperUser();
		demoInstance.createWebsite();
		demoInstance.createPage();
		demoInstance.createWidget();
		System.out.println("End Inserts");

		System.out.println("Begin Updates");
		demoInstance.updateDeveloper("charlie", true, "333-444-5555");
		demoInstance.updateWidget("head345", 3);
		demoInstance.updatePage("CNET-", "CNET");
		demoInstance.updateRoles("CNET", "Home", "charlie", "bob");
		System.out.println("End Updates");

		System.out.println("Begin Deletes");
		demoInstance.deleteAddress("alice", true);
		demoInstance.deleteWidget("Contact");
		demoInstance.deletePage("Wikipedia");
		demoInstance.deleteWebsite("CNET");
		System.out.println("End Deletes");

		System.out.println("Begin stored procedures");
		StoredProcedureDao proceduredao = StoredProcedureDao.getInstance();
		proceduredao.getUnansweredQuestions("Exam");
		proceduredao.endorsedUserForWeek(Date.valueOf("2018-10-01"));
		System.out.println("End stored procedures");
	}

}