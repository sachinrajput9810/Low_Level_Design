import java.util.ArrayList;
import java.util.List;

interface FileSystemElement {
    void ls(int indent) ;
    void openAll(int indent) ;
    int getSize() ;
    FileSystemElement cd(String name) ;
    String getName() ;
    Boolean isFolder() ;
}

class File implements FileSystemElement{

    private String name ;
    private int size ;

    public File(String name , int size){
        this.name = name ;
        this.size = size ;
    }

    @Override
    public void ls(int indent) {
        String indentSpace = " ".repeat(indent) ;
        System.out.println(indentSpace + name);
    }

    @Override
    public void openAll(int indent) {
        String indentSpace = " ".repeat(indent) ;
        System.out.println(indentSpace + name);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public FileSystemElement cd(String name) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean isFolder() {
        return false;
    }
}

class Folder implements FileSystemElement{

    private String name ;
    private List<FileSystemElement> children ;

    public Folder(String name){
        this.name = name ;
        children = new ArrayList<>() ;
    }

    public void add(FileSystemElement element){
        children.add(element) ;
    }

    @Override
    public void ls(int indent) {
        String indentSpace = " ".repeat(indent) ;
        for(FileSystemElement child : children){
            if(child.isFolder()){
                System.out.println(indentSpace + "+" + child.getName()) ;
            }
            else{
                System.out.println(indentSpace  + child.getName()) ;
            }
        }
    }

    @Override
    public void openAll(int indent) {
        String indentSpace = " ".repeat(indent) ;
        System.out.println(indentSpace + "+" + name) ;
        for(FileSystemElement child : children){
            child.openAll(indent + 4) ;
        }

    }

    @Override
    public int getSize() {
        int totalSize = 0 ;
        for(FileSystemElement child : children ){
            totalSize += child.getSize() ;
        }
        return totalSize ;
    }

    @Override
    public FileSystemElement cd(String name) {
        for(FileSystemElement child : children){
            if(child.getName().equals(name)){
                return child ;
            }
        }
        return null ;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean isFolder() {
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Folder root = new Folder("root") ;

        File file1 = new File("file1.txt" , 10) ;
        File file2 = new File("file2.txt" , 20) ;
        root.add(file1) ;
        root.add(file2) ;

        Folder folder1 = new Folder("docs") ;
        File file3 = new File("resume.pdf" , 15) ;
        File file4 = new File("report.docx" , 25) ;
        folder1.add(file3) ;
        folder1.add(file4) ;
        root.add(folder1) ;

        Folder folder2 = new Folder("photos") ;
        File file5 = new File("photo1.jpg" , 5) ;
        File file6 = new File("photo2.jpg" , 10) ;
        folder2.add(file5) ;
        folder2.add(file6) ;
        root.add(folder2) ;

        root.ls(0) ;
        System.out.println("--------------------") ;
        root.openAll(0) ;
        System.out.println("--------------------") ;
        System.out.println("Total Size: " + root.getSize()) ;
    }
}