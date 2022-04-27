package Kiskis;


import Kiskis.Module.School;
import Kiskis.Module.Student;


import java.io.*;

import java.util.Arrays;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Main
{
    public static void main(String[] args) throws InterruptedException {
        School school = CreateSchool();

        String switchOption;
        System.out.println("Menu:");
        System.out.println("0. EXIT");
        System.out.println("1. All students data");
        System.out.println("2. Server");
        System.out.println("3. Marshall");

        while (true)
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Choose: ");
            switchOption = scan.nextLine();

            switch (switchOption)
            {
                case "0":
                {
                    System.exit(0);
                    break;
                }
                case "1":
                {
                    System.out.println(school);
                    break;
                }
                case "2":
                {
                    Client client = new Client();

                    client.setIPAddress("127.0.0.1");
                    client.setPort(1001);

                    client.start();

                    client.join();

                    break;
                }
                case "3":
                {
                    Kiskis.Jaxb jaxb = new Kiskis.Jaxb();
                    String XMLContent = jaxb.Marshall(school);
                    System.out.println("XML");
                    System.out.println(XMLContent);

                    School pschohol = jaxb.unmarshalll(XMLContent, false);
                    System.out.println("OBJECT");
                    System.out.println(pschohol);

                    boolean Same = school.toString().equals(pschohol.toString());
                    System.out.println("passed: " + Same);
                    break;
                }

                default:
                {
                    System.out.println("Choose an option");
                }
            }
        }
    }


    /**
     *
     * @param input source
     * @return content
     * @throws IOException in case of error
     */
    private static String resourceToString(InputStream input) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader((input), StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null)
        {
            builder.append(line).append('\n');
        }
        return builder.toString();
    }

    /**
     *
     * @return list of students
     */
    public static School CreateSchool()
    {
        School school = new School();
        school.setSchoolName("VIKO");

        Student student = CreateStudent("Justas", "Math");
        Student student1 = CreateStudent("Petras", "C#");
        Student student2 = CreateStudent("Jonas", "Literature");
        Student student3 = CreateStudent("Virgilijus", "Engineering");
        Student student4 = CreateStudent("Nikolajus", "JavaScript");
        Student student5 = CreateStudent("Gabija", "Philosophy");
        Student student6 = CreateStudent("Augustas", "Psychology");

        List <Student> studentList = Arrays.asList(student, student1, student2, student3, student4, student5, student6);

        school.setStudents(studentList);

        return school;
    }

    /**
     *
     * @param Name student name
     * @param Subject student subject
     * @return student
     */
    public static Student CreateStudent(String Name, String Subject)
    {
        Student student = new Student();
        student.setName(Name);
        student.setSubject(Subject);

        return student;
    }
}
