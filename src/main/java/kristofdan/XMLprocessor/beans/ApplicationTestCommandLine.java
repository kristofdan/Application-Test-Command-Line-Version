package kristofdan.XMLprocessor.beans;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * Can have up to three parameters: the path to the XML file,
 * the ordering of the result (optional): "byName" or "byOccurence", default is "byName",
 * and a String (optional, only names starting with this will be shown).
 */

public class ApplicationTestCommandLine {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Error: missing parameters (you need to provide"
                    + "at least one parameter (the file path)");
            return;
        }
        try {
            Parameters parameters = Util.parseParameters(args);
            
            File file = new File(parameters.filePath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            Handler handler;
            if (parameters.ordering == OrderType.BYNAME) {
                handler = new Handler(OrderType.BYNAME, parameters.prefix);
            } else {
                handler = new Handler(OrderType.BYNUMBEROFOCCURRENCES, parameters.prefix);
            }
            
            saxParser.parse(file, handler);   
            
            ArrayList<Person> result = handler.getResult();
            System.out.println(result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
