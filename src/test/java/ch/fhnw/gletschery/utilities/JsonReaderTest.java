package ch.fhnw.gletschery.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ch.fhnw.gletschery.model.GameData;

import java.io.File;
import java.io.FileWriter;

public class JsonReaderTest {

    @Test
    public void testReadValidJson() throws Exception {
        String tempPath = "temp_gamedata.json";
        String jsonContent = "{\"players\":[],\"cardPairs\":[],\"settings\":{},\"ui\":{},\"design\":{}}";
        FileWriter writer = new FileWriter(tempPath);
        writer.write(jsonContent);
        writer.close();

        GameData data = JsonReader.read(tempPath);
        assertNotNull(data);

        new File(tempPath).delete();
    }

    @Test
    public void testReadWithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> JsonReader.read(null));
    }

    @Test
    public void testReadInvalidFilePath() {
        GameData data = JsonReader.read("non_existing_file.json");
        assertNull(data);
    }
}
