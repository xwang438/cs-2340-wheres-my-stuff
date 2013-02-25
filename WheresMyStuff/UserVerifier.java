public class UserVerifier{
  
  private static String[] usernames = new String[1];
  private static String[] passwords = new String[1];
  private int userIndex;
  
  public UserVerifier(){
    usernames[0] = "Admin1";
    passwords[0] = "password1";
    userIndex = -1;
  }
  
  public String getUser(int index){
    if(index > usernames.length){
      System.out.println("Invalid Index");
      return null;
    }
    else{
      return usernames[index];
    }
  }
  
  public String getPassword(int index){
    if(index > passwords.length){
      System.out.println("Invalid Index");
      return null;
    }
    else{
      return passwords[index];
    }
  }
  
  public boolean checkUsername(String user){
    for(int i = 0; i < usernames.length; i++){
      if(usernames[i].equals(user)){
        userIndex = i;
        return true;
      }
    }
    return false;
  }
  
  public boolean checkPassword(String pass){
      if(passwords[userIndex].equals(pass)){
        userIndex = -1;
        return true;
      }
    return false;
  }
  
  public Boolean addUser(String newUser, String newPassword){
    for(int i =0; i < usernames.length; i++){
      if(usernames[i].equals(newUser)){
        System.out.println("Username already exists.");
        return false;
      }
    }
    String[] tempUsers = usernames;
    usernames = new String[usernames.length+1];
    String[] tempPass = passwords;
    passwords = new String[passwords.length+1];
    for(int i = 0; i < tempUsers.length; i++){
      usernames[i] = tempUsers[i];
      passwords[i] = tempPass[i];
    }
    usernames[usernames.length-1] = newUser;
    passwords[passwords.length-1] = newPassword;
    return true;
    }
  
  public boolean loginCheck(String username, String password){
    if(this.checkUsername(username)){
      if(this.checkPassword(password)){
        return true;
      }
      System.out.println("Incorrect Password. Please try again.");
    }
    System.out.println("Incorrect Username. Please try again.");
    return false;
  }
      
  public String toString(){
    String result = new String("");
    for(int i=0; i < usernames.length; i++){
      result = result + "User" + i + ": " + usernames[i] + "; Password: " + passwords[i] + "\n";
    }
    return result;
  }
  
  
  
}