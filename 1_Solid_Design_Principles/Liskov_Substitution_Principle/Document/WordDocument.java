public class WordDocument extends PrintableDocument{

    @Override
    public void open(String fileName) {
        System.out.println("Opening Word document..." );
    }
    @Override
    public void save() {
        System.out.println("Saving Word document..." );
    }
    @Override
    public void print(String fileName) {
        System.out.println("Printing Word document..." );
    }
}
