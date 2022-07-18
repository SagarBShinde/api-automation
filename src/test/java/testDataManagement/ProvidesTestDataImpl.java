/**
 * 
 */
package testDataManagement;

import java.util.ArrayList;
import java.util.List;

import automation.pojo.user.PetStoreUser;

/**
 * @author sagar.shinde
 *
 */
public class ProvidesTestDataImpl implements ProvidesTestData {

	public List<PetStoreUser> CreateUserWithArrayPayload() {
		
		List<PetStoreUser> petStoreUsers = new ArrayList<PetStoreUser>();
		petStoreUsers.add(createUser(0,"MikeR","Bjorn","Ironside","Bjorn.Ironside@vikings.com","Bjorn12$","444-44-44",0));
		petStoreUsers.add(createUser(0,"ArthurK","Lagartha","Loathbroke","Lagartha@vikings.com","Lagartha12$","555-55-55",0));
		
		return petStoreUsers;
	}
	
	

private PetStoreUser createUser
	(int id, String userName, String firstName, String lastName, String email, String password, String phone, int status) {

		PetStoreUser u1 = new PetStoreUser();
		u1.setId(0);
		u1.setUserName(userName);
		u1.setFirstName(firstName);
		u1.setLastName(lastName);
		u1.setEmail(email);
		u1.setPassword(password);
		u1.setPhone(phone);
		u1.setUserStatus(status);
	
	return u1;

}
}
