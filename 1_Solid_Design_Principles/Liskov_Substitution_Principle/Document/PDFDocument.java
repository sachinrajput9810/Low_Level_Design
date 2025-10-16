public class PDFDocument extends PrintableDocument{


    @Override
    public void open(String fileName) {
        System.out.println("Opening PDF document..." );
    }

    @Override
    public void save() {
        System.out.println("Saving PDF document..." );
    }

    @Override
    public void print(String fileName) {
        System.out.println("Printing PDF document..." );
    }



}
