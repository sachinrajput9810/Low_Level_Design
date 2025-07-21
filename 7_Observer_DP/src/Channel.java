import java.util.ArrayList;
import java.util.List;

public class Channel implements IChannel{
    private String name ;
    private String latestVideo ;
    private List<ISubscriber> subscribers;

    public Channel(String name) {
        this.name = name;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if(!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for(ISubscriber subscriber : subscribers){
            subscriber.update();
        }
    }

    // extra helper to upload a new video and notify subscribers
    public void uploadVideo(String videoTitle){
        this.latestVideo = videoTitle;
        System.out.println(name + " uploaded a new video: " + videoTitle); ;
        notifySubscribers();
    }

    public String getLatestVideo() {
        return "Checkout our latest video: " + latestVideo ;
    }
}
