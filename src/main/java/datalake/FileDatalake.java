package datalake;

import aemet.Weather;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileDatalake implements Datalake{
    private final File root;


    public FileDatalake(File root) {
        this.root = root;
    }

    public void save(List<Weather> weatherList){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMDD");
        String name = "\\" + formatter.format(LocalDate.now())+ ".events";
        try {
            File file = new File(root + name );

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            Gson gson = new Gson();
            String jsonObject = gson.toJson(weatherList);
            fos.write(jsonObject.getBytes());

            fos.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}
