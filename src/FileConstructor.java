import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.UUID;

public class FileConstructor {
    float x, y;
    UUID avto;

    public FileConstructor(float x, float y, UUID avto) {
        this.x = x;
        this.y = y;
        this.avto = avto;
    }

    public void createDocument() throws Exception {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("V8Exch:_1CV8DtUD");
                Attr rootAtr = doc.createAttribute("xmlns:V8Exch");
                rootAtr.setValue("http://www.1c.ru/V8/1CV8DtUD/");
                rootElement.setAttributeNode(rootAtr);

                Attr rootAtr2 = doc.createAttribute("xmlns:v8");
                rootAtr2.setValue("http://v8.1c.ru/data");
                rootElement.setAttributeNode(rootAtr2);

                Attr rootAtr3 = doc.createAttribute("xmlns:xsi");
                rootAtr3.setValue("http://www.w3.org/2001/XMLSchema-instance");
                rootElement.setAttributeNode(rootAtr3);
            doc.appendChild(rootElement);

            // staff elements
            Element v8Exch = doc.createElement("V8Exch:Data");
            rootElement.appendChild(v8Exch);

            Element register = doc.createElement("InformationRegisterRecordSet.ПоказателиОдометраАвтомобилей");
            v8Exch.appendChild(register);

            Element filter = doc.createElement("Filter");
            register.appendChild(filter);

                Element recorder = doc.createElement("Recorder");
                    Attr atrFilter = doc.createAttribute("xsi:type");
                    atrFilter.setValue("DocumentRef.ПутевойЛист");
                    recorder.setAttributeNode(atrFilter);
                recorder.appendChild(doc.createTextNode(avto.toString()));
                filter.appendChild(recorder);

            Element records = doc.createElement("records");
            register.appendChild(records);

            Element record = doc.createElement("record");
            records.appendChild(record);

            Element recorder2 = doc.createElement("Recorder");
                Attr atrrecorder = doc.createAttribute("xsi:type");
                atrrecorder.setValue("DocumentRef.ПутевойЛист");
                recorder2.setAttributeNode(atrrecorder);
                recorder2.appendChild(doc.createTextNode(avto.toString()));
            record.appendChild(recorder2);

            Element period = doc.createElement("Period");
            period.appendChild(doc.createTextNode("2022-09-15T06:22:33"));
            record.appendChild(period);

            Element active = doc.createElement("Active");
            active.appendChild(doc.createTextNode("true"));
            record.appendChild(active);

            Element avtomobile = doc.createElement("Автомобиль");
            avtomobile.appendChild(doc.createTextNode(avto.toString()));
            record.appendChild(avtomobile);

            Element org = doc.createElement("Организация");
            org.appendChild(doc.createTextNode(avto.toString()));
            record.appendChild(org);

            Element value = doc.createElement("Показатель");
            value.appendChild(doc.createTextNode("666"));
            record.appendChild(value);

//            Element recorder = doc.createElement("Recorder");
//                Attr atrFilter = doc.createAttribute("xsi:type");
//                atrFilter.setValue("DocumentRef.ПутевойЛист");
//                recorder.setAttributeNode(rootAtr);
//            filter.appendChild(recorder);


            // set attribute to staff element
//            Attr attr = doc.createAttribute("id");
//            attr.setValue("1");
//            staff.setAttributeNode(attr);

            // shorten way
            // staff.setAttribute("id", "1");

            // firstname elements
//            Element firstname = doc.createElement("firstname");
//            firstname.appendChild(doc.createTextNode("yong"));
//            staff.appendChild(firstname);
//
//            // lastname elements
//            Element lastname = doc.createElement("lastname");
//            lastname.appendChild(doc.createTextNode("mook kim"));
//            staff.appendChild(lastname);
//
//            // nickname elements
//            Element nickname = doc.createElement("nickname");
//            nickname.appendChild(doc.createTextNode("mkyong"));
//            staff.appendChild(nickname);
//
//            // salary elements
//            Element salary = doc.createElement("salary");
//            salary.appendChild(doc.createTextNode("100000"));
//            staff.appendChild(salary);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("D:\\MV-lab\\Java\\src\\ClientVoloshynAvto\\out\\out.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}
