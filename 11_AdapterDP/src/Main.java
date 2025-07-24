public class Main {
    public static void main(String[] args) {
//        OldTemperatureSensor oldTemperatureSensor = new OldTemperatureSensor() ;
//        TemperatureSensor temperatureSensor = new TemperatureSensorAdapter(oldTemperatureSensor) ;
//        System.out.println(temperatureSensor.getTemperatureInCelsius()) ;

        Client client = new Client() ;
        XMLDataProvider xmlDataProvider = new XMLDataProvider() ;
        IReports xmlToJsonAdapter = new XMLToJsonAdapter(xmlDataProvider) ;
        client.generateReport("Lula Sholves:32" , xmlToJsonAdapter) ;

    }
}

interface IReports{
    String getJsonReport(String data) ;
}

class XMLDataProvider {
    // eg "Alice:32"
    public String getXMLData(String data){
        int idx = data.indexOf(":") ;
        String name = data.substring(0 , idx) ;
        int age = Integer.parseInt(data.substring(idx+1)) ;
        return "<users>" +
                "<name>" + name + "</name>" +
                "<age>" + age + "</age>" +
                "</users>" ;
    }
}

class XMLToJsonAdapter implements IReports{
    private XMLDataProvider xmlDataProvider ;
    public XMLToJsonAdapter(XMLDataProvider xmlDataProvider){
        this.xmlDataProvider = xmlDataProvider ;
    }
    public String getJsonReport(String data){
        String xmlData = xmlDataProvider.getXMLData(data) ;
        // logic to convert xml data string to json string
        String name = xmlData.substring(xmlData.indexOf("<name>") + 6 , xmlData.indexOf("</name>")) ;
        int age = Integer.parseInt(xmlData.substring(xmlData.indexOf("<age>") + 5 , xmlData.indexOf("</age>"))) ;
        return "{\"name\":\"" + name + "\" , \"age\":" + age + "}" ;
    }
}

class Client{
    public void generateReport(String data , IReports report){
        System.out.println("Processed data  => ") ;
        String reportData = report.getJsonReport(data) ;
        System.out.println(reportData) ;
    }
}




//class OldTemperatureSensor{
//    public double getTemperatureInFahrenheit(){
//        return 97.6 ;
//    }
//}
//
//interface TemperatureSensor{
//    public double getTemperatureInCelsius() ;
//}
//
//class TemperatureSensorAdapter implements TemperatureSensor{
//    private OldTemperatureSensor oldTemperatureSensor ;
//    public TemperatureSensorAdapter(OldTemperatureSensor oldTemperatureSensor){
//        this.oldTemperatureSensor = oldTemperatureSensor ;
//    }
//    public double getTemperatureInCelsius(){
//        return (oldTemperatureSensor.getTemperatureInFahrenheit() - 32) / 1.8 ;
//    }
//}