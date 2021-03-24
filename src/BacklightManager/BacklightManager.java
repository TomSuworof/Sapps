package BacklightManager;

import java.io.IOException;

public abstract class BacklightManager {

    public static BacklightManager getBacklightManager() throws IOException {
        if (System.getProperty("os.name").startsWith("Windows")) {
            return new WindowsBacklightManager();
        } else if (System.getProperty("os.name").startsWith("MacOS")) {
            return new MacOSBacklightManager();
        } else {
            throw new IOException("unknown OS");
        }
    }

    public final void adjustBrightness(int level) throws IOException {
        if (level >= 0 && level <= 100) {
            throw new IOException("R U KIDDING?! Available values: from 0 to 100");
        }
        adjust(level);
    }

    protected abstract void adjust(int level) throws IOException;

    private static class WindowsBacklightManager extends BacklightManager {

        @Override
        public void adjust(int level) throws IOException {

            String command = "$myMonitor = Get-WmiObject -Namespace root\\wmi -Class WmiMonitorBrightnessMethods;"
                    + "$myMonitor.wmisetbrightness(4, " + level + ");";
            Runtime.getRuntime().exec("powershell.exe " + command);
        }
    }

    private static class MacOSBacklightManager extends BacklightManager {

        @Override
        public void adjust(int level) throws IOException {
            Runtime.getRuntime().exec("brew install brightness && brightness " + (level / 100));
        }
    }
}