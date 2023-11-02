import okhttp3.*;

import java.io.IOException;

/**
 * This class allows controlling an LED on a raspberry pi using rpi-io-controller-server
 */
public class LedController {

    private OkHttpClient client = new OkHttpClient();
    private String rpiAddress;
    /**
     * Creates an LedController that sends command to the Raspberry Pi at the specified address
     * @param rpiAddress - The IP address of the Raspberry Pi that will be used
     */
    public LedController(String rpiAddress) {
        this.rpiAddress = rpiAddress;
    }

    /**
     * Set the speed at which the LED is toggled
     * @param speed - A decimal/double value that is between 0.1 and 0.9
     * @return - Returns the response message from the rpi-io-controller-server or an error message
     */
    public String setLedToggleSpeed(double speed) {
        Request request = new Request.Builder()
                .url("http://" + rpiAddress + "/commands/ledControl")
                .post(
                    RequestBody.create(
                    "{\"speed\":" + speed + "}",
                            MediaType.get("application/json")
                    )
                )
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.message();
        } catch (IOException ex) {
            return "An error occurred: " + ex.getMessage();
        }
    }
}
