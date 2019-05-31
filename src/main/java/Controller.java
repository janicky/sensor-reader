import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
                byte[] data = loadBytes(fileChooser.getSelectedFile().getPath());
                String content = new String(data);
                System.out.println(content);
            } catch (IOException e) {
                view.displayError(e.getMessage());
            }
        }
    }

    private byte[] loadBytes(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }
}
