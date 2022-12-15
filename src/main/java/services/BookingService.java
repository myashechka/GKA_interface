package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Booking;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public class BookingService {
    private final Gson gson = new Gson();
    public Booking getBooking(long id) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpUriRequest httpGet = new HttpGet("http://localhost:8080/booking/" + id);
        HttpResponse response1 = httpclient.execute(httpGet);
        HttpEntity entity1 = response1.getEntity();
        Booking read = gson.fromJson(EntityUtils.toString(entity1), Booking.class);
        httpclient.close();
        return read;

    }

    public List<Booking> getBookingByUserId(long id) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpUriRequest httpGet = new HttpGet("http://localhost:8080/booking/user_id/" + id);
        HttpResponse response1 = httpclient.execute(httpGet);
        HttpEntity entity1 = response1.getEntity();
        Type type = new TypeToken<List<Booking>>(){}.getType();
        List<Booking> read = gson.fromJson(EntityUtils.toString(entity1), type);
        httpclient.close();
        return read;

    }

    public Booking postBooking(Booking booking) throws IOException {
        String json = gson.toJson(booking);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        StringEntity requestEntity = new StringEntity(
                json,
                ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost("http://localhost:8080/booking");
        postMethod.setEntity(requestEntity);

        HttpResponse rawResponse = httpclient.execute(postMethod);
        HttpEntity entity = rawResponse.getEntity();
        Booking read = gson.fromJson(EntityUtils.toString(entity), Booking.class);
        httpclient.close();
        return read;
    }
}