public class Subscriber implements ISubscriber {
    private Channel channel ;
    private String name ;

    public Subscriber(Channel channel , String name) {
        this.channel = channel ;
        this.name = name ;
    }

    @Override
    public void update() {
        System.out.println(name + " received a new video: " + channel.getLatestVideo());
    }


}
