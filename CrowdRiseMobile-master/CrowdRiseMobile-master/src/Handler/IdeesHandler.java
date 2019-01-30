package Handler;

import Entity.Idee;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class IdeesHandler extends DefaultHandler {

     private Vector ideeVector;

    public IdeesHandler() {
        ideeVector = new Vector();
    }

    public Idee[] getIdee() {
        Idee[] ideeTab = new Idee[ideeVector.size()];
        ideeVector.copyInto(ideeTab);
        return ideeTab;
    }

    private Idee currentIdee;
    private String currentBalise;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("mydata")) {

            currentIdee = new Idee();
            currentBalise = "mydata";

        } else if (qName.equals("Id")) {
            currentBalise = "Id";
        } else if (qName.equals("Name")) {
            currentBalise = "Name";
        } else if (qName.equals("Description")) {
            currentBalise = "Description";
        }else if (qName.equals("Date_Debut")) {
            currentBalise = "Date_Debut";
        } else if (qName.equals("Date_Fin")) {
            currentBalise = "Date_Fin";
        } else if (qName.equals("Remuneration")) {
            currentBalise = "Remuneration";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("mydata")) {

            ideeVector.addElement(currentIdee);

            currentIdee = null;
        } else if (qName.equals("Id")) {
            currentBalise = "";
        } else if (qName.equals("Name")) {
            currentBalise = "";
        } else if (qName.equals("Description")) {
            currentBalise = "";
        }else if (qName.equals("Date_Debut")) {
            currentBalise = "";
        } else if (qName.equals("Date_Fin")) {
            currentBalise = "";
        } else if (qName.equals("Remuneration")) {
            currentBalise = "";
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        
        if (currentIdee != null) {
            String res ;
            if (currentBalise.equals("Id")) {
                res = new String(ch, start, length).trim();
                currentIdee.setId(Integer.parseInt(res));
            }
            if (currentBalise.equals("Name")) {
                res = new String(ch, start, length).trim();
                currentIdee.setNom(res);
            }
            if (currentBalise.equals("Description")) {
                res = new String(ch, start, length).trim();
                currentIdee.setDescription(res);
            }else if (currentBalise.equals("Date_Debut")) {
                res = new String(ch, start, length).trim();
                currentIdee.setDebut(res);
        } else if (currentBalise.equals("Date_Fin")) {
                res = new String(ch, start, length).trim();
                currentIdee.setFin(res);
        } else if (currentBalise.equals("Remuneration")) {
                res = new String(ch, start, length).trim();
                currentIdee.setRemuneration_totale(Double.parseDouble(res));
        }
        }
    }
}
