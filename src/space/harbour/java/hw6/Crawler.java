package space.harbour.java.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler implements Runnable {
    private Queue<URL> toVisit;
    private final Set<String> alreadyvisited;
    private ExecutorService executor;

    public Crawler() {
        toVisit = new LinkedList<>();
        alreadyvisited = new HashSet<>();
        executor = Executors.newFixedThreadPool(10);
    }

    public void setQueue(Queue<URL> queue) {
        toVisit = queue;
    }

    private static String getContentOfWebPage(URL url) {
        final StringBuilder content = new StringBuilder();

        try (
                InputStream is = url.openConnection().getInputStream();
                InputStreamReader in = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(in); ) {
            String inputLine;

            while ((inputLine = br.readLine()) != null)
                content.append(inputLine);
        } catch (IOException e) {
            System.out.println("Failed to retrieve content of " + url.toString());
            e.printStackTrace();
        }

        return content.toString();
    }

    @Override
    public void run() {
        while (!toVisit.isEmpty()) {
            URL visit = toVisit.poll();
            synchronized (alreadyvisited) {
                alreadyvisited.add(visit.toString());
            }
            String content = getContentOfWebPage(visit);

            String urlRegex = "((https?):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
            Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
            Matcher urlMatcher = pattern.matcher(content);

            while (urlMatcher.find()) {
                String url = content.substring(urlMatcher.start(0), urlMatcher.end(0));
                try {
                    synchronized (alreadyvisited) {
                        if (!alreadyvisited.contains(url)) {
                            toVisit.add(new URL(url));
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void runParser() {
        for(int i = 0; i < 10; i ++) {
            executor.submit(this);
        }

        executor.shutdown();
    }

    public Set<String> getVisited() {
        synchronized (alreadyvisited) {
            return alreadyvisited;
        }
    }

    public ExecutorService getExecutor() {
        return executor;
    }
}