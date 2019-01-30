
package Handler;

import Entity.Solution;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author userpc
 */
public class SolutionHandler extends DefaultHandler{
    
     private Vector solutionVector;

    public SolutionHandler() {
        solutionVector = new Vector();
    }

    public Solution[] getSol() {
        Solution[] solutionTab = new Solution[solutionVector.size()];
        solutionVector.copyInto(solutionTab);
        return solutionTab;
    }

    private Solution currentSolution;
    private String currentBalise;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("mydata")) {

            currentSolution = new Solution();
            currentBalise = "mydata";

        }else if (qName.equals("Id")) {
            currentBalise = "Id";
        } else if (qName.equals("tache")) {
            currentBalise = "tache";
        }else if (qName.equals("remuneration")) {
            currentBalise = "remuneration";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("mydata")) {

            solutionVector.addElement(currentSolution);

            currentSolution = null;
        } else if (qName.equals("Id")) {
            currentBalise = "";
        } else if (qName.equals("tache")) {
            currentBalise = "";
        }else if (qName.equals("remuneration")) {
            currentBalise = "";
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        
        if (currentSolution != null) {
            String res ;
            if (currentBalise.equals("Id")) {
                res = new String(ch, start, length).trim();
                currentSolution.setId(Integer.parseInt(res));
            }
            if (currentBalise.equals("tache")) {
                res = new String(ch, start, length).trim();
                currentSolution.setTache(res);
            }
            if (currentBalise.equals("remuneration")) {
                res = new String(ch, start, length).trim();
                currentSolution.setRemuneration(Double.valueOf(res));
        }
        }
    }
    
}
