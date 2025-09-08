
interface IDocReader{
    void unlockDocument(String fileName, String password);
}

class RealDocReader implements IDocReader{

    @Override
    public void unlockDocument(String fileName, String password) {
        System.out.println("Document named " + fileName + " is Unlocked");
    }
}

class User{
    String name ;
    Boolean isPremiumUser ;
    public User(String name, Boolean isPremiumUser) {
        this.name = name;
        this.isPremiumUser = isPremiumUser;
    }
    public Boolean isPremiumUser() {
        return isPremiumUser;
    }
}

class ProtectionProxyDocReader implements IDocReader{
    private IDocReader realDocReader ;
    private User user ;
    public ProtectionProxyDocReader(IDocReader realDocReader, User user) {
        this.realDocReader = realDocReader;
        this.user = user;
    }

    @Override
    public void unlockDocument(String fileName, String password) {
        System.out.println("User Name: " + user.name + " is being verified with Password: " + password);
        if(user.isPremiumUser()){
            realDocReader.unlockDocument(fileName, password);
        }
        else{
            System.out.println("User is not a premium user");
        }
    }
}


public class Main {
    public static void main(String[] args) {
        IDocReader realDocReader = new RealDocReader();
        User user = new User("John Doe", false);
        IDocReader protectionProxyDocReader = new ProtectionProxyDocReader(realDocReader, user);
        protectionProxyDocReader.unlockDocument("document1", "password1");
    }
}