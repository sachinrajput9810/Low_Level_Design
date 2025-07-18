
import java.io.FileWriter;
import java.util.ArrayList;

public class BadCode_DocumentEditor{

    public static void main(String[] args) {
        BadCode_DocumentEditor editor = new BadCode_DocumentEditor() ;
        editor.addText("This is the first line of the document.");
        editor.addText("This is the second line of the document.");
        editor.addImage("image1.jpg");
        editor.addImage("image2.png");
        System.out.println("Rendered Document:\n" + editor.renderDocument());
        editor.saveToFile();
    }


    private ArrayList<String> documentElements;
    private String renderDocument ;

    public BadCode_DocumentEditor() {
        this.documentElements = new ArrayList<>();
        this.renderDocument = "";
    }

    public void addText(String text){
        if (text != null && !text.isEmpty()) {
            documentElements.add(text);
        }
    }

    public void addImage(String imagePath){
        if (imagePath != null && !imagePath.isEmpty()) {
            documentElements.add("[Image: " + imagePath + "]");
        }
    }

    public String renderDocument() {
        if(renderDocument.isEmpty()){
            StringBuilder sb = new StringBuilder() ;
            for(String element : documentElements){
                if(element.length() > 4 && (element.endsWith(".jpg") || element.endsWith(".png"))){
                    sb.append("[Image: ").append(element).append("]").append("/n");
                } else {
                    sb.append(element).append("\n");
                }
            }
            renderDocument = sb.toString();
        }
        return renderDocument;
    }

    public void saveToFile(){
        try {
            FileWriter writer = new FileWriter("document.txt");
            writer.write(renderDocument());
            writer.close();
            System.err.println("Document saved successfully to document.txt");
        } catch (Exception e) {
            System.err.println("Error saving document: " + e.getMessage());
        }
    }


    


}