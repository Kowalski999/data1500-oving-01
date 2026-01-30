// DataGenerator.java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataGenerator {

	  public static void main(String[] args) {
        // Ta inn filnavn for den nye filen fra kommandolinje
        String filnavn = args[0];
        // Tips: Ta inn antall linjer som skal genereres fra args[1]
        // Tips: Husk å "kaste" type til int
        // Skriv kode her ...
          int antallLinjer = Integer.parseInt(args[1]);
        // Bruk BuffereWriter for å skrive brukerdata til filen
        try (BufferedWriter skriver = new BufferedWriter(new FileWriter(filnavn))) {
            for (int i = 1; i <= antallLinjer; i++) {
                // Formatter linjen for brukerdata slik som spesifisert i oppgaveteksten (README fil)
                // Tips: kan bruke String.format metoden for det
                String output = i + ",bruker" + i + "@epost.no,Navn Navnesen " + i;
                // Tips: bruk skriver.write metoden for å skrive den formatterte linjen til filen 
                // Tips: bruk skriver.newLine() for å skrive en linjeskift tegn til filen
                skriver.write(output);
                skriver.newLine();
            }
            // Tips: skriv ut til stdout (System.out i java) om hvor mange linjer er blitt skrevet til filen
            System.out.println("Antall linjer: " + antallLinjer);
        } catch (IOException e) {
			      e.printStackTrace();
		    }

    }

}
