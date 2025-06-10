package ch.fhnw.gletschery.utilities;

import ch.fhnw.gletschery.model.GameData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/**
 * Die Klasse {@code JsonReader} stellt eine Hilfsmethode bereit, um eine JSON-Datei
 * einzulesen und deren Inhalt in ein {@link GameData}-Objekt zu deserialisieren.
 *
 * <p>Diese Klasse nutzt die Jackson-Bibliothek, um die JSON-Daten in ein Java-Objekt umzuwandeln.
 */
public class JsonReader {
    /**
     * Liest die angegebene JSON-Datei ein und wandelt sie in ein {@link GameData}-Objekt um.
     *
     * @param file Der Pfad zur JSON-Datei, die eingelesen werden soll.
     * @return Ein {@link GameData}-Objekt, das aus der JSON-Datei erstellt wurde,
     *         oder {@code null}, wenn ein Fehler beim Lesen oder Parsen auftritt.
     * @throws IllegalArgumentException Wenn der {@code file}-Parameter {@code null} ist.
     */
    public static GameData read(String file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            GameData gameData = objectMapper.readValue(new File(file), GameData.class);
            return gameData;
        } catch (Exception e) {
            System.err.println("Cannot read JSON-File");
            e.printStackTrace();
            return null;
        }
    }
}
