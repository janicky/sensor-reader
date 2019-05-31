public class Application {
    public static void main(String[] args) {
        View view = new View("Sensor reader");
        new Controller(view);
    }
}
