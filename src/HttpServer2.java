import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServer2 {
    public static void main(String[] arg) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("10.1.0.48", 1337), 0);
        server.createContext("/test", new  MyHttpHandler());
        server.start();
    }

}
