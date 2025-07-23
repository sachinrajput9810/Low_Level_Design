import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // creating notification service
        NotificationService notificationService = NotificationService.getInstance() ;
        // creating logger (Observer 1)
        Logger logger = new Logger() ;
        // Notification Engine (Observer 2)
        NotificationEngine notificationEngine = new NotificationEngine() ;
        notificationEngine.addNotificationStrategy(new EmailNotificationStrategy("2UoGk@example.com")) ;
        notificationEngine.addNotificationStrategy(new SMSNotificationStrategy("1234567890")) ;
        notificationEngine.addNotificationStrategy(new PushNotificationStrategy()) ;

        INotification notification = new simpleNotification("This is a simple notification") ;
        notification = new TimeStampDecorator(notification) ;
        notification = new SignatureDecorator(notification, "John Doe") ;

        notificationService.sendNotification(notification) ;
    }
}

// Creating Notification and its decorators
interface INotification{
    public String getContent();
}

class simpleNotification implements INotification{
    private String text ;
    public simpleNotification(String message){
        this.text = message ;
    }
    public String getContent(){
        return text ;
    }
}

abstract class NotificationDecorator implements INotification{
    protected INotification notification ;
    public NotificationDecorator(INotification notification){
        this.notification = notification ;
    }
}

class TimeStampDecorator extends NotificationDecorator{
    public TimeStampDecorator(INotification notification){
        super(notification);
    }
    public String getContent(){
        return notification.getContent() + " - " + LocalDateTime.now() ;
    }
}

class SignatureDecorator extends NotificationDecorator {
    private String signature ;
    public SignatureDecorator(INotification notification, String signature) {
        super(notification);
        this.signature = signature;
    }

    public String getContent() {
        return notification.getContent() + " - " + signature;
    }
}

// Observer pattern Components

interface IObserver {
    public void update();
}

interface IObservable{
    public void addObserver(IObserver observer);
    public void removeObserver(IObserver observer);
    public void notifyObservers();
}

class NotificationObservable  implements IObservable{
    private List<IObserver> observers  = new ArrayList<>() ;
    private INotification currentNotification  = null ;
    public void addObserver(IObserver observer){
        observers.add(observer) ;
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer) ;
    }

    @Override
    public void notifyObservers() {
        for(IObserver observer : observers){
            observer.update() ;
        }
    }

    public INotification getNotification(){
        return currentNotification ;
    }

    public void setCurrentNotification(INotification notification){
        this.currentNotification = notification ;
        notifyObservers() ;
    }

    public String getNotificationContent(){
        return currentNotification.getContent() ;
    }

}

/*============================
       NotificationService
=============================*/

// The NotificationService manages notifications. It keeps track of notifications.
// Any client code will interact with this service.

// Singleton class
class NotificationService{

    private NotificationObservable notificationObservable ;
    private List<INotification> notifications = new ArrayList<>() ;
    private static NotificationService instance  = null ;


    public NotificationObservable getObservable(){
        return notificationObservable ;
    }

    private NotificationService(){
        notificationObservable = new NotificationObservable() ;
    }

    public static NotificationService getInstance(){
        if(instance == null){
            instance = new NotificationService() ;
        }
        return instance ;
    }

    public void sendNotification(INotification notification){
        notifications.add(notification) ;
        notificationObservable.setCurrentNotification(notification) ;
    }

}

// Concrete Observers

class Logger implements IObserver{
    private NotificationObservable notificationObservable ;

    public Logger(NotificationObservable notificationObservable){
        this.notificationObservable = notificationObservable ;
        notificationObservable.addObserver(this) ;
    }

    public Logger(){
        this.notificationObservable = NotificationService.getInstance().getObservable() ;
        notificationObservable.addObserver(this) ;
    }

    @Override
    public void update() {
        System.out.println("Logger: Notification received : " + notificationObservable.getNotificationContent()) ;
    }

}

// Strategy Pattern component

interface INotificationStrategy{
    public void sendNotification(String content) ;
}

class EmailNotificationStrategy implements INotificationStrategy{
    private String email ;
    public EmailNotificationStrategy(String email){
        this.email = email ;
    }
    public void sendNotification(String content){
//        Simulate the process of sending an email notification,
//         representing the dispatch of messages to users via email.
        System.out.println("Email Notification Sent to " + email + " : " + content) ;
    }
}

class SMSNotificationStrategy implements INotificationStrategy{
    private String phoneNumber ;
    public SMSNotificationStrategy(String phoneNumber){
        this.phoneNumber = phoneNumber ;
    }
    public void sendNotification(String content){
        System.out.println("SMS Notification Sent to " + phoneNumber + " : " + content) ;
    }
}

class PushNotificationStrategy implements INotificationStrategy{
    public void sendNotification(String content){
        System.out.println("Push Notification Sent : " + content) ;
    }
}

// Creating Notification Engine

class NotificationEngine implements IObserver{
    private NotificationObservable notificationObservable ;
    private List<INotificationStrategy> notificationStrategies = new ArrayList<>() ;
    public NotificationEngine() {
        this.notificationObservable = NotificationService.getInstance().getObservable() ;
        notificationObservable.addObserver(this) ;
    }

    public void addNotificationStrategy(INotificationStrategy notificationStrategy){
        notificationStrategies.add(notificationStrategy) ;
    }

    @Override
    public void update() {
        String content = notificationObservable.getNotificationContent() ;
        for(INotificationStrategy notificationStrategy : notificationStrategies){
            notificationStrategy.sendNotification(content) ;
        }
    }
}


















