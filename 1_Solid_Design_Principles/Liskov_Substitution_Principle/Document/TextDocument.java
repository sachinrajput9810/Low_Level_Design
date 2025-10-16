public class TextDocument extends Document {

    @Override
    public void open(String fileName) {
        System.out.println("Opening Text document..." );
    }

    @Override
    public void save() {
        System.out.println("Saving Text document..." );
    }
}
