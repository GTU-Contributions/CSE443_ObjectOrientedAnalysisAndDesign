package Part2.ServerAndClient;

import java.io.Serializable;

public class ClientProfile implements Serializable{
    private int credits;
    private String userName;
    private String password;

    ClientProfile(String userName, String password){
        this.userName = userName;
        this.password = password;
        credits = 0;
    }

    public boolean userIdentification(String userName, String password){
        return (this.userName.equals(userName) && this.password.equals(password));
    }

    public String getUserName(){
        return this.userName;
    }
    public int getCredits(){
        return credits;
    }
    public void increaseCredits(int credits){
        this.credits += credits;
    }
    public void decreaseCredits(int credits){
        this.credits -= credits;
    }
}
