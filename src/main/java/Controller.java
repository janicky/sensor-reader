import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class Controller {
    private View view;
    private String sensorsPath;

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
                sensorsPath = fileChooser.getSelectedFile().getPath();
                load();

                Thread thread = new Thread(new SensorsUpdater());
                thread.start();

            } catch (IOException e) {
                view.displayError(e.getMessage());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private String loadContent(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        return reader.lines().collect(Collectors.joining()).replaceAll("\\s+","");
    }

    public class SensorsUpdater implements Runnable {

        public void run() {
            while (sensorsPath != null) {
                try {
                    Thread.sleep(1000);
                    load();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void load() throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        String content = loadContent(sensorsPath);
        JSONArray sensors = (JSONArray) parser.parse(content);
        view.updateTable(sensors);
    }
}
