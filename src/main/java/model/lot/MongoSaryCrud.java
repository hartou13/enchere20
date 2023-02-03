package model.lot;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class MongoSaryCrud {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoSaryCrud(String databaseName, String collectionName) {
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase(databaseName);
        collection = database.getCollection(collectionName);
    }
    public MongoSaryCrud(){
        mongoClient = MongoClients.create("mongodb+srv://test:test@cluster0.xfzivgg.mongodb.net/?retryWrites=true&w=majority");
        // mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("enchere");
        collection = database.getCollection("datasary");
        // collection = database.getCollection("mycollection");
    }
    public void create(Sary sary) {
        Document document = new Document("idLot", sary.getIdLot())
                                .append("sary", sary.getSary());
        collection.insertOne(document);
    }

    public Sary read(int idLot) {
        Document document = collection.find(new Document("idLot", idLot)).first();
        Sary sary = new Sary();
        sary.setIdLot(document.getInteger("idLot"));
        sary.setSary(document.getString("sary"));
        return sary;
    }

    public void update(Sary sary) {
        collection.updateOne(new Document("idLot", sary.getIdLot()),
                new Document("$set", new Document("sary", sary.getSary())));
    }

    public void delete(int idLot) {
        collection.deleteOne(new Document("idLot", idLot));
    }

    public void close() {
        mongoClient.close();
    }
    public List<Sary> getSaryLot(Integer idLot) {
        
        List<Sary> saryList = new ArrayList<>();
        for (Document document : collection.find(new Document("idLot", idLot))) {
            Sary sary = new Sary();
            sary.setIdLot(document.getInteger("idLot"));
            sary.setSary(document.getString("sary"));
            saryList.add(sary);
        }
        System.out.println(collection);
        System.out.println("longueur "+idLot+"="+saryList.size());
        return saryList;
    }
}
