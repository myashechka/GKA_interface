package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Booking;
import models.Ticket;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class TicketService {
    private final Gson gson = new Gson();

    public List<Ticket> getTickets(String from, String to, String date) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpUriRequest httpGet = new HttpGet("http://localhost:8080/ticket/" + date + "/"+from+"/"+to);
        HttpResponse response1 = httpclient.execute(httpGet);
        HttpEntity entity1 = response1.getEntity();
        Type type = new TypeToken<List<Ticket>>(){}.getType();
        List<Ticket> read = gson.fromJson(EntityUtils.toString(entity1), type);
        httpclient.close();
        return read;
    }
}
