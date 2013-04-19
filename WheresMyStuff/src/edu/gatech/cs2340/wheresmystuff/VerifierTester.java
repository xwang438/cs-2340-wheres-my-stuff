package edu.gatech.cs2340.wheresmystuff;

import android.test.ActivityInstrumentationTestCase2;

/**
 * JUnit test class for UserVerifier
 * @author Kyle
 * @version 4/16/2013
 *
 */


public class VerifierTester extends ActivityInstrumentationTestCase2<LoginActivity>{
	
	private static UserVerifier uv;
	private Item item;
	private Database db;
	private LoginActivity la;
	
	/**
	 * constructor for testing
	 */
	public VerifierTester() {
		super(LoginActivity.class);
	}

	/**
	 * get the LoginView Activity and add a new item named test
	 */
	protected void setUp() throws Exception {
		la = getActivity();
		item = new Item("test");
		db.insertItem(item);
		uv = new UserVerifier(la);
	}
	/**
	 * Main method. When run it performs a simple test of the UserVerifier class.
	 * specifically, it tests the UserVerifier class' ability to discern between existing
	 * users, and users that have not been added to the database yet.
	 */
	public static void main(String[] args) {
		
		System.out.println("We will now check to see if UserVerifier can check a provided usename and password. usrname: Admin1, passwrd: password1.");
		if(uv.loginCheck("admin@gatech.edu", "admin1") == true){
			System.out.println("the provided usename and input was correct. UserVerifier can successfully check preexisting users.");
		}
		else{System.out.println("UserVerifier failed to confirm a preexisting user. Have a nice day.");
		System.out.println("We will now check to see if UserVerifier can catch a bad username and password. username: BaDnAmE, password: BaDwOrD.");
		if(uv.loginCheck("BaDnAmE@lol.com", "BaDwOrD") == false){
			System.out.println("UserVerifier caught the bad username and password.");			
		}
		else{System.out.println("UserVerifier failed to catch the bad username and password.");
		
		}
		}

	}

}
