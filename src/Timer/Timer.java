package Timer;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.*;

public class Timer {
    private static final String filepath = "src/Timer/alarm.wav";

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter time (format: 'min:sec' or 'sec'): ");
            String time_str = reader.readLine();
            reader.close();
            int minutes = 0;
            int seconds;

            if (time_str.contains(":")) {
                String[] time_spl = time_str.split(":");
                minutes = Integer.parseInt(time_spl[0]);
                seconds = Integer.parseInt(time_spl[1]);
            } else {
                seconds = Integer.parseInt(time_str);
            }

            int time_sec = minutes * 60 + seconds;

            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filepath)));

            Thread.sleep(1000L * time_sec);
            System.out.println("TIME IS OVER");

            if (SystemTray.isSupported()) {
                displayNotification(time_str);
            }

            clip.start();
            waitWhilePlaying(clip);
            clip.close();

        } catch (NumberFormatException nfe) {
            System.out.println("Wrong number. Restart the program");
        } catch (InterruptedException ie) {
            System.out.println("Something went wrong");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void waitWhilePlaying(Clip clip) throws InterruptedException {
        while (!clip.isRunning()) {
            Thread.sleep(10);
        }
        while (clip.isRunning()) {
            Thread.sleep(10);
        }
    }

    private static void displayNotification(String time) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("Timer/image.png");
        TrayIcon trayIcon = new TrayIcon(image, "AlarmClock");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray alarm icon");
        tray.add(trayIcon);
        trayIcon.displayMessage("TIME IS OVER", time, TrayIcon.MessageType.INFO);
    }
}
