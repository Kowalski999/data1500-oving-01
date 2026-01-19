import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LesStudenter {

    class Student {

        int id;
        String navn;
        String program;

        public Student(int id, String navn, String program) {

            this.id = id;
            this.navn = navn;
            this.program = program;

        }

        @Override
        public String toString() {
            return "Student{id=" + id + ", navn='" + navn + "', program='" + program +"'}";
        }

    }

    public static void main(String[] args) {
        try (Scanner leser = new Scanner(new File(args[0]))) {

            LesStudenter program = new LesStudenter();

            while (leser.hasNextLine()) {

                String[] info = leser.nextLine().split(",");
                if (info.length == 3) {

                    Student s = program.new Student(Integer.parseInt(info[0]), info[1], info[2]);
                    System.out.println(s);

                }
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }
}