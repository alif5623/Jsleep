package laodeAlifJsleepFN;


public class Account extends Serializable implements FileParser
{
    public String name;
    public String email;
    public String password;

    public Account(int id, String name, String email, String password)
    {
        //super(id);
       // this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String toString(){
        return "\nID: " + this.id + "\nName: " + this.name + "\nEmail: " + this.email + "\nPassword: " + this.password;
    }
    public Object write(){
        return null;
    }
    public Boolean read(String a){
        return true;
    }
}
