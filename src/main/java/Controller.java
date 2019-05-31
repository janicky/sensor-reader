import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Controller {
    private View view;


    public Controller(View view) {
        this.view = view;

        initializeListeners();
    }

    private void initializeListeners() {
        view.addSelectSourceListener(e -> onSelectSource());
    }

    private void onSelectSource() {
        JFileChooser fileChooser = view.selectFile();
        if (fileChooser != null) {
            try {
                JSONParser parser = new JSONParser();
                String content = loadContent(fileChooser.getSelectedFile().getPath());
                JSONArray sensors = (JSONArray) parser.parse(content);
                Iterator<JSONObject> iterator = sensors.iterator();
                while (iterator.hasNext()) {
                    JSONObject obj = iterator.next();
                    System.out.println(obj.get("SensorApp"));
                }

            } catch (IOException e) {
                view.displayError(e.getMessage());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private String loadContent(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        return reader.lines().collect(Collectors.joining()).replaceAll("\\s+","");
    }
}
