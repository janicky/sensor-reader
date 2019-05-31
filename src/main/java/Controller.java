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

    }
}
