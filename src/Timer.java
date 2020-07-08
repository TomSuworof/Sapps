import java.applet.Applet;
import java.applet.AudioClip;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Timer {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter time (format: 'min:sec' or 'sec'): ");
            String time_str = reader.readLine();
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
            AudioClip sound = Applet.newAudioClip(new URL("alarm.wav"));
            Thread.sleep(1000 * time_sec);
            sound.play();
            System.out.println("TIME IS OVER");

        } catch (IOException ioe) {
            System.out.println("Wrong input");
        } catch (NumberFormatException nfe) {
            System.out.println("Wrong number. Restart the program");
        } catch (InterruptedException ie) {
            System.out.println("Something went wrong");
        }
    }
}
