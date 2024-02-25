package src.classes;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLManager {

    private static final String XML_FILE_PATH = "tasks.xml";

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("tasks");
            doc.appendChild(rootElement);

            for (Task task : tasks) {
                Element taskElement = doc.createElement("task");
                rootElement.appendChild(taskElement);

                taskElement.setAttribute("position", String.valueOf(task.getPosition()));

                Element titleElement = doc.createElement("title");
                titleElement.appendChild(doc.createTextNode(task.getTitle()));
                taskElement.appendChild(titleElement);

                Element descElement = doc.createElement("description");
                descElement.appendChild(doc.createTextNode(task.getDescription()));
                taskElement.appendChild(descElement);

                Element creationDateElement = doc.createElement("creationDate");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                creationDateElement.appendChild(doc.createTextNode(task.getCreationDate().format(formatter)));
                taskElement.appendChild(creationDateElement);

                Element creationTimeElement = doc.createElement("creationTime");
                formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                creationTimeElement.appendChild(doc.createTextNode(task.getCreationTime().format(formatter)));
                taskElement.appendChild(creationTimeElement);

                Element endDateElement = doc.createElement("endDate");
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                endDateElement.appendChild(doc.createTextNode(task.getEndDate().format(formatter)));
                taskElement.appendChild(endDateElement);

                Element endTimeElement = doc.createElement("endTime");
                formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                endTimeElement.appendChild(doc.createTextNode(task.getEndTime().format(formatter)));
                taskElement.appendChild(endTimeElement);

                Element isDoneElement = doc.createElement("isDone");
                isDoneElement.appendChild(doc.createTextNode(String.valueOf(task.isTodoDone())));
                taskElement.appendChild(isDoneElement);
            }

            // Save the XML document in the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(XML_FILE_PATH));

            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException exc) {
            exc.printStackTrace();
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(XML_FILE_PATH);

            if (file.exists()) {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(file);

                doc.getDocumentElement().normalize();

                // Get nodes
                NodeList taskList = doc.getElementsByTagName("task");

                for (int i = 0; i < taskList.getLength(); i++) {
                    Node taskNode = taskList.item(i);

                    if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element taskElement = (Element) taskNode;

                        int position = Integer.parseInt(taskElement.getAttribute("position"));
                        String title = taskElement.getElementsByTagName("title").item(i).getTextContent();
                        String description = taskElement.getElementsByTagName("description").item(i).getTextContent();

                        String creationDateString = taskElement.getElementsByTagName("creationDate").item(i)
                                .getTextContent();
                        LocalDate creationDate = LocalDate.parse(creationDateString);

                        String creationTimeString = taskElement.getElementsByTagName("creationTime").item(i)
                                .getTextContent();
                        LocalDateTime creationTime = LocalDateTime.parse(creationTimeString);

                        String endDateString = taskElement.getElementsByTagName("endDate").item(i).getTextContent();
                        LocalDate endDate = LocalDate.parse(endDateString);

                        String endTimeString = taskElement.getElementsByTagName("endTime").item(i).getTextContent();
                        LocalDateTime endTime = LocalDateTime.parse(endTimeString);

                        boolean isDone = Boolean
                                .parseBoolean(taskElement.getElementsByTagName("isDone").item(i).getTextContent());

                        Task task = new Task(position, title, description, creationDate, creationTime, endDate, endTime,
                                isDone);
                        tasks.add(task);
                    }
                }

            }
        } catch (ParserConfigurationException | IOException | SAXException exc) {
            exc.printStackTrace();
        }

        return tasks;
    }
}