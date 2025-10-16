import java.lang.annotation.Documented;

public class Main {
    
    public static void main(String[] args) {
        Document doc = new Document();
        doc.open("file.txt");

        PrintableDocument pdf = new PDFDocument() ;
        pdf.open("file.pdf");
        pdf.save() ;
        pdf.print("file.pdf");

        Document text = new TextDocument() ;
        text.open("file.txt");
        text.save() ;

    }
}
