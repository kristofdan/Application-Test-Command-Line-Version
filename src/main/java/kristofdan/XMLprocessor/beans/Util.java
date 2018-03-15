package kristofdan.XMLprocessor.beans;

/**
 * Contains static helper methods.
 
 */

public class Util {
    
    public static Parameters parseParameters(String[] args) {
        Parameters parameters = new Parameters();
        parameters.filePath = args[0];
        
        if (args.length == 1) {
            parameters.ordering = OrderType.BYNAME;
            parameters.prefix = "";
        } else {
            switch (args[1]) {
                case "byName":
                    parameters.ordering = OrderType.BYNAME;
                    parameters.prefix = args.length == 2 ? "" : args[2];
                    break;
                case "byOccurence":
                    parameters.ordering = OrderType.BYNUMBEROFOCCURRENCES;
                    parameters.prefix = args.length == 2 ? "" : args[2];
                    break;
                default:            
                    parameters.ordering = OrderType.BYNAME;
                    parameters.prefix = args[1];
                    break;
            }
        }
        
        return parameters;
    }
    
}