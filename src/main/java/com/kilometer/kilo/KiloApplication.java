package com.kilometer.kilo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

// import com.google.api.services.storage.model.Notification;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import model.lot.Lot;
import model.lot.MongoSaryCrud;
import model.lot.Sary;

@SpringBootApplication(scanBasePackages = { "controller", "com.kilometer.kilo", "model.lot.sary" })
@CrossOrigin
public class KiloApplication {

	public static void main(String[] args) {
		// MongoSaryCrud msc = new MongoSaryCrud();
		// Sary temp = new Sary();
		// temp.setIdLot(0);
		// temp.setSary("init");
		// // msc.create(temp);
		// // msc.delete(1);
		// msc.close();
		// System.out.println("vita");
		SpringApplication.run(KiloApplication.class, args);
		// initializeFirebaseAdmin();

	}

	private static void initializeFirebaseAdmin() {
		try {
			FileInputStream serviceAccount = new FileInputStream(
					"enchereapp-41590-firebase-adminsdk-9801j-407d847f5d.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			Notification notification = Notification
					.builder()
					.setTitle("test")
					.setBody("test")
					.build();

			FirebaseApp.initializeApp(options);
			Message message = Message.builder()
					.putData("key1", "value1")
					.putData("key2", "value2")
					.setNotification(notification)
					.setTopic("over")
					.build();

			try {
				String response = FirebaseMessaging.getInstance().send(message);
				System.out.println("Successful sent message: " + response);
			} catch (FirebaseMessagingException e) {
				System.out.println("Error sending message: " + e.getMessage());
			}
		} catch (FileNotFoundException e) {
			System.out.println("Clée privée introuvable! Tout ce qui est Firebase Admin ne fonctionnera pas.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(
					"Erreur lors de l'initialisation de Firebase Admin! Tout ce qui est Firebase Admin ne fonctionnera pas.");
		}
	}

	public static void sendNotification(Lot lot) {
		Notification notification = Notification.builder().setTitle("enchere terminee")
				.setBody(lot.getNomLot() + " a ete vendu").build();
		sendNotification(notification);
	}

	public static void sendNotification(Notification notif) {
		try {
			FileInputStream serviceAccount = new FileInputStream(
					"enchereapp-41590-firebase-adminsdk-9801j-407d847f5d.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			FirebaseApp.initializeApp(options);
			// FirebaseApp.getApps().size();
			Message message = Message.builder()
					.putData("key1", "value1")
					.putData("key2", "value2")
					.setNotification(notif)
					.setTopic("over")
					.build();

			try {
				String response = FirebaseMessaging.getInstance().send(message);
				System.out.println("Successful sent message: " + response);
			} catch (FirebaseMessagingException e) {
				System.out.println("Error sending message: " + e.getMessage());
			}
		} catch (FileNotFoundException e) {
			System.out.println("Clée privée introuvable! Tout ce qui est Firebase Admin ne fonctionnera pas.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(
					"Erreur lors de l'initialisation de Firebase Admin! Tout ce qui est Firebase Admin ne fonctionnera pas.");
		}
	}

}
