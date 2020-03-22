package com.jm.projet.filrouge;

import java.io.*;
import java.nio.file.Path;

public class CityLoader {

    public static void main(String[] args) throws IOException {

        Path filePathIn = Path.of("/home/bnp-renault-011/Cours/Code/spring/project/backend/src/main/resources/villes_france.csv");
        Path filePathOut = Path.of("/home/bnp-renault-011/Cours/Code/spring/project/backend/src/main/resources/villes_france.sql");

        String line;
        String[] splitLine;
        String name;
        String postalCode ;
        String latitude;
        String longitude;
        String id_city ;
        String departement ;
        String sql ;
        int i = 0 ;

        try {
            BufferedReader reader = new BufferedReader(new FileReader (filePathIn.toFile()));
            BufferedWriter writer = new BufferedWriter (new FileWriter (filePathOut.toFile ( )));
            line = reader.readLine();
            while (line != null) {
                splitLine = line.split(",");
                id_city = splitLine[0];
                departement = splitLine[1];
                splitLine[2] = splitLine[2].replace("\'", " ");

                name = splitLine[2];
                postalCode = splitLine[3];
                latitude=  splitLine[14];
                longitude= splitLine[15];



                sql = "INSERT INTO city (id_city, latitude, longitude, name, postal_code, department_id_department ) VALUES ("+ id_city +","+latitude
                +","+ longitude+",'"+name+"',"+postalCode+","+departement+");\n";

                writer.write (sql);
                line= reader.readLine ();

                i+=1 ;
                if( i == 1000) {
                   // writer.close ();
                   // writer = new BufferedWriter (new FileWriter (filePathOut.toFile ( )));
                    writer.flush ();
                    i= 0;
                }
            }
            reader.close ();
            writer.close ();
        } catch (IOException ioEx) {
            System.out.println("Error load : " + ioEx.getMessage());
        }
    }
}
