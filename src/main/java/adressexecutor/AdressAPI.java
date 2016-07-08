package adressexecutor;

import flexjson.JSONDeserializer;
import flexjson.JSONException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.*;

/**
 * Created by dfyz on 02.07.2016.
 */
public class AdressAPI {
    public static void main(String[] args) {
        StringBuilder uri = new StringBuilder("https://extract-beta.api.smartystreets.com/?auth-id=62883753-d3ff-0636-2c8f-c3a219c9adb2&auth-token=u0KvgkdEU4sswXCb8woF");
        StringBuilder sb = new StringBuilder();

        URL url;
        HttpURLConnection connection;
        ReadableByteChannel rbc = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            url = new URL(uri.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String msg = "DEL MAR PHOTONICS INC. Moscow Russia 4119 Twilight Ridge San Diego, CA 92130 United States. Phone: (858) 876-3133 Fax: (858) 630-2376. Visit website. Product";
            writer.write(msg.toLowerCase());
            writer.flush();
            writer.close();
            os.close();
            int responseCode = connection.getResponseCode();
            System.out.println(responseCode);
            String response = "";
            if ( responseCode == HttpsURLConnection.HTTP_OK){
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line=br.readLine())!= null){
                    response+=line;
                }
            }else {
                System.out.println("Ошибочка!");
            }
            System.out.println(response);

            try {
                List<HashMap<String, Object>> data = new JSONDeserializer<List<HashMap<String, Object>>>()
                        .use(null, ArrayList.class)
                        .use("values", HashMap.class)
                        .deserialize(response);
            } catch (JSONException ex) {
                System.out.println("Ошибочка!");
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}
