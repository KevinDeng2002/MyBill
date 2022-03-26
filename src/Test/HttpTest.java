package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTest {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.baidu.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader bufferedReader  =new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            Object[] lines = bufferedReader.lines().toArray();
            for(Object l:lines){
                System.out.println((String) l);
            }
//            while((line=bufferedReader.readLine())!=null)  // 每次调用都会读一行
//            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

