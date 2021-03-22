package BacklightManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BacklightManager {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            Robot robot = new Robot();
            int key = KeyEvent.VK_F12;
            while (!input.equalsIgnoreCase("exit")) {
                if ("press".equalsIgnoreCase(input)) {
                    robot.keyPress(key);
                    System.out.println("Key pressed");
                    robot.keyRelease(key);
                    System.out.println("Key released");
                } // does not work :(
                input = reader.readLine();
            }
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }
}