package services;

import com.google.gson.Gson;
import models.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class UserService {
    private final Gson gson = new Gson();
    public User getUser(long id) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpUriRequest httpGet = new HttpGet("http://localhost:8080/user/" + id);
        HttpResponse response1 = httpclient.execute(httpGet);
        HttpEntity entity1 = response1.getEntity();
        User read = gson.fromJson(EntityUtils.toString(entity1), User.class);
        httpclient.close();
        return read;
    }

    public User getUser(String email) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpUriRequest httpGet = new HttpGet("http://localhost:8080/user/email/" + email);
        HttpResponse response1 = httpclient.execute(httpGet);
        HttpEntity entity1 = response1.getEntity();
        User read = gson.fromJson(EntityUtils.toString(entity1), User.class);
        httpclient.close();
        return read;
    }

    public User postUser(User user) throws IOException {
        String json = gson.toJson(user);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        StringEntity requestEntity = new StringEntity(
                json,
                ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost("http://localhost:8080/user");
        postMethod.setEntity(requestEntity);

        HttpResponse rawResponse = httpclient.execute(postMethod);
        HttpEntity entity = rawResponse.getEntity();
        User read = gson.fromJson(EntityUtils.toString(entity), User.class);
        httpclient.close();
        return read;
    }

    public User putUser(User user) throws IOException {
        String json = gson.toJson(user);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        StringEntity requestEntity = new StringEntity(
                json,
                ContentType.APPLICATION_JSON);

        HttpPut putMethod = new HttpPut("http://localhost:8080/user");
        putMethod.setEntity(requestEntity);

        HttpResponse rawResponse = httpclient.execute(putMethod);
        HttpEntity entity = rawResponse.getEntity();
        User read = gson.fromJson(EntityUtils.toString(entity), User.class);
        httpclient.close();
        return read;
    }
}


