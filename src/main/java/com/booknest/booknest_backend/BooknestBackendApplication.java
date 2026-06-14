package com.booknest.booknest_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootApplication
@EnableScheduling
public class BooknestBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooknestBackendApplication.class, args);
	}

	@Scheduled(fixedRate = 30000)
	public void callApi() {

		try {

			URL url = new URL(
				"https://official-joke-api.appspot.com/random_joke"
			);

			HttpURLConnection connection =
					(HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");

			System.out.println(
				"Response Code: " + connection.getResponseCode()
			);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
