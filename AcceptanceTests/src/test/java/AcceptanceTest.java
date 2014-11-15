import org.testng.annotations.Test;

@Test(timeOut = 5000)
public class AcceptanceTest {


    public void testWalkingSkeleton() throws Exception {
        ConsoleApplication server = ConsoleApplication.launchJar("VFS-Server\\target\\VFS-Server.jar");
        server.waitForAStart();

        ConsoleApplication client = ConsoleApplication.launchJar("VFS-Client\\target\\VFS-Client.jar");
        client.waitForAStart();

        client.enterCommand("connect nothost testuser");
        client.ensureMessage("Cann't connect to defined host");

        client.enterCommand("connect localhost testuser");
        client.ensureMessage("Connection established");

        client.enterCommand("quit");
        client.ensureMessage("Connection closed");

        client.shutDown();
        server.shutDown();
    }
}