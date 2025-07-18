public class Singleton {


    private static Singleton instance = null ;

    private Singleton(){
        // private constructor to prevent instantiation
        System.out.println("Singleton instance created");
    }

    public static Singleton getInstance() {
        if(instance == null) {
            synchronized(Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                    System.out.println("Sync block executed to create Singleton instance");
                }
            }
        }
        return instance;
    }

     public static void main(String[] args) {
        // Testing the Singleton pattern
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println("Are both instances equal? " + (instance1 == instance2));
    }



}