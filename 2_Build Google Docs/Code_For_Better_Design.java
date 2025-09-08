import java.io.FileWriter;
import java.util.ArrayList;
public class Code_For_Better_Design{

    public interface DocumentElement {
        String render();
    }

    public static class TextElement implements DocumentElement {
        private String text;
        public TextElement(String text) {
            this.text = text;
        }
        public String render() {
            return text;
        }
    }

    public static class ImageElement implements DocumentElement{
        private String imagePath ;
        public ImageElement(String imagePath) {
            this.imagePath = imagePath;
        }
        public String render(){
            return "[Image: " + imagePath + "]";
        }
    }

    public static class NewLine implements DocumentElement {
        public String render() {
            return "\n";
        }
    }

    public static class TabSpace implements DocumentElement {
        public String render() {
            return "\t";
        }
    }

    public static class Document{

        public Document() {
            this.elements = new ArrayList<>();
        }

        private ArrayList<DocumentElement> elements;
        public void addElement(DocumentElement element) {
            if (element != null) {
                elements.add(element);
            }
        }

        public String render(){
            StringBuilder sb = new StringBuilder() ;
            for(DocumentElement element : elements){
                sb.append(element.render());
            }
            return sb.toString();
        }

        
    }

    public interface Persistence{
        void save(String data) ;
    }

    public static  class saveToFile implements Persistence{
        private String fileName;
        public saveToFile(String fileName) {
            this.fileName = fileName;
        }
        FileWriter writer ;
        public void save(String data){
            try {
                writer = new FileWriter(fileName);
                writer.write(data);
                writer.close();
                System.out.println("Document saved successfully to " + fileName);
            } catch (Exception e) {
                System.err.println("Error saving document: " + e.getMessage());
            }
        }

    }

    public static class saveToDB implements Persistence{
        private String databaseName;
        public saveToDB(String databaseName) {
            this.databaseName = databaseName;
        }
        public void save(String data){
            System.out.println("Saving document to " + databaseName);
        }
    }

    public static class DocumentEditor {
        private Document document;
        private Persistence persistence;
        private String renderedDocument ;

        public DocumentEditor(Document document ,Persistence persistence) {
            this.document = document;
            this.persistence = persistence;
            this.renderedDocument = "";
        }

        public void addText(String text) {
            if (text != null && !text.isEmpty()) {
                document.addElement(new TextElement(text));
            }
        }

        public void addImage(String imagePath) {
            if (imagePath != null && !imagePath.isEmpty()) {
                document.addElement(new ImageElement(imagePath));
            }
        }
        public void addNewLine() {
            document.addElement(new NewLine());
        }
        public void addTabSpace() {
            document.addElement(new TabSpace());
        }
        public String renderDocument() {
            if (renderedDocument.isEmpty()) {
                renderedDocument = document.render();
            }
            return renderedDocument;
        }

        public void saveDocument() {
            persistence.save(renderDocument());
        }

    
       
    }

     public static  void main(String[] args) {
            Document doc = new Document() ;
            Persistence persistence = new saveToFile("document1.txt");
            DocumentEditor editor = new DocumentEditor(doc, persistence) ;
            editor.addText("This is the first line of the document.");
            editor.addNewLine();
            editor.addText("This is the second line of the document.");
            editor.addNewLine();
            editor.addImage("image1.jpg");
            editor.addTabSpace();
            editor.addImage("image2.png");
            editor.addNewLine();
            editor.addTabSpace();
            editor.addImage("image3.png");
            System.out.println("Rendered Document:\n" + editor.renderDocument());
            editor.saveDocument();
        }



}

