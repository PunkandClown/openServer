import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;


public class MyHttpHandler implements HttpHandler {
public String  index = "C:\\П-285\\index.html";
    public String  Reg = "C:\\П-285\\indexReg.html";

    @Override

    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue=null;
        if("GET".equals(httpExchange.getRequestMethod())) {
            System.out.println("Отправка начальной стр");
            handleResponse(httpExchange);
            System.out.println("end.");

        } else if("POST".equals(httpExchange.getRequestMethod())) {
            System.out.println("пост");
            handleRequest(httpExchange);
        }
        System.out.println("Конец взаимодействия");
    }

    private void handleResponse(HttpExchange httpExchange)  throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        byte[] arrayss = Files.readAllBytes(Paths.get(index));
        httpExchange.sendResponseHeaders(200, arrayss.length);
        outputStream.write(arrayss);
        outputStream.flush();
        outputStream.close();
        System.out.println("Конец отправки");
    }
    private void handleRequest(HttpExchange httpExchange)  throws IOException {
        BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(httpExchange.getRequestBody()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        for (;(line = bufferedReader.readLine()) != null;) {
            stringBuilder.append(line);
        }
        String information = stringBuilder.toString();

        String Str = information.substring(information.indexOf('{') +1, information.lastIndexOf('}'));
        StringTokenizer st = new StringTokenizer(Str, ",");
            String Nick = st.nextToken().trim();
            String Nam = st.nextToken().trim();
            String pas = st.nextToken().trim();
        HashmapClass.base(Nick,Nam,pas, httpExchange);
        HashmapClass.KeysValue();
        bufferedReader.close();
        System.out.println("accept");
    }
    public static void handleBadResponse(HttpExchange httpExchange)  throws IOException {
        String  Reg = "C:\\П-285\\indexReg.html";
        OutputStream outputStream = httpExchange.getResponseBody();
        byte[] arrayss = Files.readAllBytes(Paths.get(Reg));
        httpExchange.sendResponseHeaders(200, arrayss.length);
        outputStream.write(arrayss);
        outputStream.flush();
        outputStream.close();
        System.out.println("Конец отправки");
    }



}