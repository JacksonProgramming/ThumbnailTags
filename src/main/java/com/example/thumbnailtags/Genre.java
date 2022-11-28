package com.example.thumbnailtags;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Genre {

    private HashMap<String,String> genres;
    private File genreFile;

    public Genre(String fileName) {
        this.genres = new HashMap<>();
        this.genreFile = new File(fileName);
    }

    public boolean load() {
        try {
            Scanner fileReader = new Scanner(Paths.get(this.genreFile.getName()));
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] lineParts = line.split(":");
                this.genres.putIfAbsent(lineParts[0],lineParts[1]);
            }
            return true;
        } catch (Exception e) {
            System.out.println("File load error" + e);
            return false;
        }
    }

    public String getGenre(String index) {
        return genres.get(index);
    }
    /*
    public ArrayList<String> getGenreNames() {
        ArrayList<String> keys = new ArrayList<>();

        for (String i: genres.keySet()) {
            keys.add(i);
        }
        return keys;
    }
    */



}
