public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel("GeeksforGeeks");
        Subscriber user1 = new Subscriber(channel , "User1") ;
        Subscriber user2 = new Subscriber(channel , "User2") ;
        channel.addSubscriber(user1);
        channel.addSubscriber(user2) ;
        channel.uploadVideo("Hum Dil De Chuke Sanam");

        // Lets unsubscribe user1
        channel.removeSubscriber(user1) ;
        channel.uploadVideo("Go DSA") ;

    }
}