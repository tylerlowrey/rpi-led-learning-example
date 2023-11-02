public class Main {
    public static void main(String[] args) {
        System.out.println("Running LED control program...");
        LedController ledController = new LedController("192.168.1.201:8080");
        System.out.println("Response: " + ledController.setLedToggleSpeed(0.9));
    }
}
