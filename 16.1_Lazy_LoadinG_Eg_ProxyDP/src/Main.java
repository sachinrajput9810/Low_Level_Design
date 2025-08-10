import java.awt.image.ImageProducer;



// IMAGE PROXY EG
interface Image{
    public void display() ;
}

class RealImage implements  Image{
    private String fileName ;
    public RealImage(String fileName){
        this.fileName = fileName ;
        loadFromDisk(fileName) ;
    }

    public void loadFromDisk(String fileName){
        System.out.println("Loading image from disk: " + fileName);
        try{
            Thread.sleep(2000) ;
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }


    public void display(){
        System.out.println("Displaying image: " + fileName);
    }
}

class ProxyImage implements Image{
    private String fileName ;
    private RealImage realImage ;

    public ProxyImage(String fileName){
        this.fileName = fileName ;
    }


    @Override
    public void display() {
        if(realImage == null){
            realImage = new RealImage(fileName) ;
        }
        realImage.display();
    }
}

public class Main {
    public static void main(String[] args) {
//        Image image1 = new RealImage("image1.jpg") ;
//        Image image2 = new RealImage("image2.jpg") ;
//        image1.display() ;
        Image image3 = new ProxyImage("image3.jpg") ;
        Image image4 = new ProxyImage("image4.jpg") ;
        image3.display() ;
    }
}

