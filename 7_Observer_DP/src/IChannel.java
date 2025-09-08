public interface IChannel {
    public void addSubscriber(ISubscriber subscriber) ;
    public void removeSubscriber(ISubscriber subscriber) ;
    public void notifySubscribers() ;
}
