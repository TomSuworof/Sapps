package BacklightManager;

import java.io.IOException;

public class BacklightManagerDemo {
    public static void main(String[] args) {
        try {
            BacklightManager manager = BacklightManager.getBacklightManager();
            manager.adjustBrightness(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
