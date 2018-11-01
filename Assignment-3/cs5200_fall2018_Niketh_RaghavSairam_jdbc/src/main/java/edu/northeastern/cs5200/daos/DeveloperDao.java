package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;

import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Phone;

public class DeveloperDao implements DeveloperImpl {
	private java.sql.Connection conn;
	private static DeveloperDao instance = null;

	private DeveloperDao() {
		try {
			this.conn = edu.northeastern.cs5200.Connection.getConnection();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static DeveloperDao getInstance() {
		if (instance == null) {
			instance = new DeveloperDao();
		}
		return instance;
	}

	private Statement statement = null;
	private ResultSet results = null;

	private final String INSERT_PERSON = "INSERT INTO person (id,last_name,first_name,username,password,email,dob) VALUE (?,?,?,?,?,?,?)";
	private final String INSERT_DEVELOPER = "INSERT INTO developer (person_id,developer_key) VALUE (?,?)";
	private final String INSERT_ADDRESS = "INSERT INTO address (street_1,street_2,city,state,zip,`primary`,person_id) VALUE (?,?,?,?,?,?,?)";
	private final String INSERT_PHONE = "INSERT INTO phone (phone,`primary`,person_id) VALUE (?,?,?)";

	private final String FIND_ALL_DEVELOPERS = "SELECT * FROM person JOIN developer ON person.id=developer.person_id";

	private final String FIND_DEVELOPER_BY_ID = "SELECT * FROM person JOIN developer ON person.id=developer.person_id AND person.id=?";
	private final String FIND_DEVELOPER_BY_ID_PHONES = "SELECT * FROM phone WHERE person_id=?";
	private final String FIND_DEVELOPER_BY_ID_ADDRESSES = "SELECT * FROM address WHERE person_id=?";

	private final String FIND_DEVELOPERS_BY_USERNAME = "SELECT * FROM person JOIN developer ON person.id=developer.person_id AND person.username=?";

	private final String FIND_DEVELOPERS_BY_CREDENTIALS = "SELECT * FROM person JOIN developer ON person.id=developer.person_id AND person.username=? AND person.password=?";

	private final String UPDATE_PERSON = "UPDATE person set id=?,last_name=?,first_name=?,username=?,password=?, email=?,dob=? WHERE id=?";
	private final String UPDATE_DEVELOPER = "UPDATE developer set person_id=?,developer_key=? WHERE person_id=?";
	private final String UPDATE_PHONE = "UPDATE phone set phone=? WHERE person_id =? AND `primary`= ? ";
	private final String UPDATE_ADDRESS = "UPDATE address set street_1=?,street_2=?,city=?,state=?,zip=? WHERE person_id=? AND `primary`=?";

	private final String DELETE_PERSON = "DELETE FROM person WHERE id=?";
	private final String DELETE_DEVELOPER = "DELETE FROM developer WHERE person_id=?";
	private final String DELETE_PHONE = "DELETE FROM phone WHERE person_id =?";
	private final String DELETE_ADDRESS = "DELETE FROM address WHERE person_id=?";
	private final String DELETE_ADDRESSOFDEVELOPER = "DELETE FROM address WHERE person_id=? AND `primary`=?";

	public void createDeveloper(Developer developer) {
		PreparedStatement pstatement = null;
		@SuppressWarnings("unused")
		int result;
		try {
			pstatement = conn.prepareStatement(INSERT_PERSON);

			pstatement.setInt(1, developer.getId());
			pstatement.setString(2, developer.getLast_name());
			pstatement.setString(3, developer.getFirst_name());
			pstatement.setString(4, developer.getUsername());
			pstatement.setString(5, developer.getPassword());
			pstatement.setString(6, developer.getEmail());
			pstatement.setDate(7, developer.getDob());
			result = pstatement.executeUpdate();

			pstatement = conn.prepareStatement(INSERT_DEVELOPER);

			pstatement.setInt(1, developer.getId());
			pstatement.setString(2, developer.getDeveloper_key());
			result = pstatement.executeUpdate();

			Collection<Phone> phones = developer.getPhones();
			if (!phones.isEmpty()) {
				for (Phone phone : phones) {
					pstatement = conn.prepareStatement(INSERT_PHONE);
					pstatement.setString(1, phone.getPhone());
					pstatement.setBoolean(2, phone.getPrimary());
					pstatement.setInt(3, developer.getId());
					result = pstatement.executeUpdate();
				}
			}

			Collection<Address> addresses = developer.getAddresses();
			if (!addresses.isEmpty()) {
				for (Address address : addresses) {
					pstatement = conn.prepareStatement(INSERT_ADDRESS);
					pstatement.setString(1, address.getStreet_1());
					pstatement.setString(2, address.getStreet_2());
					pstatement.setString(3, address.getCity());
					pstatement.setString(4, address.getState());
					pstatement.setString(5, address.getZip());
					pstatement.setBoolean(6, address.getPrimary());
					pstatement.setInt(7, developer.getId());
					result = pstatement.executeUpdate();

				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public Collection<Developer> findAllDevelopers() {
		results = null;
		statement = null;
		Collection<Developer> developers = new ArrayList<Developer>();
		try {
			statement = conn.createStatement();
			results = statement.executeQuery(FIND_ALL_DEVELOPERS);
			while (results.next()) {
				int id = results.getInt("person_id");
				String last_name = results.getString("first_name");
				String first_name = results.getString("last_name");
				String username = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				String developer_key = results.getString("developer_key");
				Developer developer = new Developer(id, developer_key, first_name, last_name, username, password, email,
						dob);
				developers.add(developer);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return developers;
	}

	public Developer findDeveloperById(int developerId) {
		Developer developer = new Developer();
		PreparedStatement pstatement = null;
		results = null;
		try {
			pstatement = conn.prepareStatement(FIND_DEVELOPER_BY_ID);
			pstatement.setInt(1, developerId);

			results = pstatement.executeQuery();
			if (results.next()) {
				int id = results.getInt("id");
				String last_name = results.getString("first_name");
				String first_name = results.getString("last_name");
				String username = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				String developer_key = results.getString("developer_key");

				developer.setId(id);
				developer.setLast_name(last_name);
				developer.setFirst_name(first_name);
				developer.setUsername(username);
				developer.setPassword(password);
				developer.setEmail(email);
				developer.setDob(dob);
				developer.setDeveloper_key(developer_key);
			}
			results = null;

			pstatement = conn.prepareStatement(FIND_DEVELOPER_BY_ID_PHONES);
			pstatement.setInt(1, developerId);
			results = pstatement.executeQuery();

			Collection<Phone> phones = new ArrayList<Phone>();
			while (results.next()) {
				String phone_number = results.getString("phone");
				Boolean primary = results.getBoolean("primary");
				Phone phone = new Phone(phone_number, primary);
				phones.add(phone);
			}
			developer.setPhones(phones);

			results = null;
			pstatement = conn.prepareStatement(FIND_DEVELOPER_BY_ID_ADDRESSES);
			pstatement.setInt(1, developerId);
			results = pstatement.executeQuery();

			Collection<Address> addresses = new ArrayList<Address>();
			while (results.next()) {
				String street_1 = results.getString("street_1");
				String street_2 = results.getString("street_2");
				String city = results.getString("city");
				String state = results.getString("state");
				String zip = results.getString("zip");
				Boolean primary = results.getBoolean("primary");
				Address address = new Address(street_1, street_2, city, state, zip, primary);
				addresses.add(address);
			}
			developer.setAddresses(addresses);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return developer;
	}

	public Developer findDeveloperByUsername(String username) {
		Developer developer = new Developer();
		PreparedStatement pstatement = null;
		results = null;
		try {
			pstatement = conn.prepareStatement(FIND_DEVELOPERS_BY_USERNAME);
			pstatement.setString(1, username);

			results = pstatement.executeQuery();
			if (results.next()) {
				int id = results.getInt("id");
				String last_name = results.getString("first_name");
				String first_name = results.getString("last_name");
				String usernameTemp = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				String developer_key = results.getString("developer_key");

				developer.setId(id);
				developer.setLast_name(last_name);
				developer.setFirst_name(first_name);
				developer.setUsername(usernameTemp);
				developer.setPassword(password);
				developer.setEmail(email);
				developer.setDob(dob);
				developer.setDeveloper_key(developer_key);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return developer;
	}

	public Developer findDeveloperByCredentials(String username, String password) {
		Developer developer = new Developer();
		PreparedStatement pstatement = null;
		results = null;
		try {
			pstatement = conn.prepareStatement(FIND_DEVELOPERS_BY_CREDENTIALS);
			pstatement.setString(1, username);
			pstatement.setString(2, password);

			results = pstatement.executeQuery();
			if (results.next()) {
				int id = results.getInt("id");
				String last_name = results.getString("first_name");
				String first_name = results.getString("last_name");
				String usernameTemp = results.getString("username");
				String passwordTemp = results.getString("password");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				String developer_key = results.getString("developer_key");

				developer.setId(id);
				developer.setLast_name(last_name);
				developer.setFirst_name(first_name);
				developer.setUsername(usernameTemp);
				developer.setPassword(passwordTemp);
				developer.setEmail(email);
				developer.setDob(dob);
				developer.setDeveloper_key(developer_key);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return developer;
	}

	public int updateDeveloper(int developerId, Developer developer) {
		int result = 0;
		PreparedStatement pstatement = null;
		try {

			pstatement = conn.prepareStatement(UPDATE_PERSON);
			pstatement.setInt(1, developer.getId());
			pstatement.setString(2, developer.getLast_name());
			pstatement.setString(3, developer.getFirst_name());
			pstatement.setString(4, developer.getUsername());
			pstatement.setString(5, developer.getPassword());
			pstatement.setString(6, developer.getEmail());
			pstatement.setDate(7, developer.getDob());
			pstatement.setInt(8, developerId);
			result = pstatement.executeUpdate();

			pstatement = conn.prepareStatement(UPDATE_DEVELOPER);
			pstatement.setInt(1, developerId);
			pstatement.setString(2, developer.getDeveloper_key());
			pstatement.setInt(3, developerId);
			result = pstatement.executeUpdate();

			Collection<Phone> phones = developer.getPhones();
			if (phones.isEmpty()) {
				pstatement = null;
				pstatement = conn.prepareStatement(DELETE_PHONE);
				pstatement.setInt(1, developerId);
				result = pstatement.executeUpdate();
			} else {
				for (Phone phone : phones) {
					pstatement = null;
					pstatement = conn.prepareStatement(UPDATE_PHONE);
					pstatement.setString(1, phone.getPhone());
					pstatement.setInt(2, developer.getId());
					pstatement.setBoolean(3, phone.getPrimary());
					result = pstatement.executeUpdate();
				}
			}

			Collection<Address> addresses = developer.getAddresses();
			if (addresses.isEmpty()) {
				pstatement = null;
				pstatement = conn.prepareStatement(DELETE_ADDRESS);
				pstatement.setInt(1, developerId);
				result = pstatement.executeUpdate();
			} else {
				for (Address address : addresses) {
					pstatement = null;
					pstatement = conn.prepareStatement(UPDATE_ADDRESS);
					pstatement.setString(1, address.getStreet_1());
					pstatement.setString(2, address.getStreet_2());
					pstatement.setString(3, address.getCity());
					pstatement.setString(4, address.getState());
					pstatement.setString(5, address.getZip());
					pstatement.setInt(6, developer.getId());
					pstatement.setBoolean(7, address.getPrimary());
					result = pstatement.executeUpdate();
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public int deleteDeveloper(int developerId) {
		int result = 0;
		PreparedStatement pstatement = null;
		try {
			pstatement = conn.prepareStatement(DELETE_PERSON);
			pstatement.setInt(1, developerId);
			result = pstatement.executeUpdate();

			pstatement = null;
			pstatement = conn.prepareStatement(DELETE_DEVELOPER);
			pstatement.setInt(1, developerId);
			result = pstatement.executeUpdate();

			pstatement = conn.prepareStatement(DELETE_PHONE);
			pstatement.setInt(1, developerId);
			result = pstatement.executeUpdate();

			pstatement = conn.prepareStatement(DELETE_ADDRESS);
			pstatement.setInt(1, developerId);
			result = pstatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public int deleteAddressofDeveloper(int developerId, Boolean primary) {
		int result = 0;
		PreparedStatement pstatement = null;
		try {

			pstatement = conn.prepareStatement(DELETE_ADDRESSOFDEVELOPER);
			pstatement.setInt(1, developerId);
			pstatement.setBoolean(2, primary);

			result = pstatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

}
