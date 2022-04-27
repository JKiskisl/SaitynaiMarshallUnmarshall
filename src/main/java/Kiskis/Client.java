package Kiskis;

import java.net.*;
import java.io.*;
import java.util.Scanner;



import Kiskis.Module.School;


//client klase
public class Client extends Thread
{

    private School school;
    private int port;
    private String IPAddress;
    private Socket clientSocket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public void run() { startConnecting(); }

    /**
     * Sets the value of IP address
     *
     */
    public void setIPAddress(String IPAddress) { this.IPAddress = IPAddress; }

    /**
     *
     * @param port sets port
     */
    public void setPort(int port) {
        this.port = port;
    }

    public void startConnecting()
    {
        try
        {
            clientSocket = new Socket(IPAddress, port);
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            printWriter.println("Hello server from client!");
            String response = bufferedReader.readLine();
            System.out.println("Client: Client server is running successfully and received " + response + " from local server.");

            BufferedInputStream bufferedInputStream = new BufferedInputStream(clientSocket.getInputStream());
            int XMLFileData;
            if ((XMLFileData = bufferedInputStream.read()) != -1)
            {
                byte[] dataBuffer = new byte[8192];
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                while ((XMLFileData = bufferedInputStream.read(dataBuffer)) != -1)
                {
                    out.write(dataBuffer, 0, XMLFileData);
                }

                byte[] data = out.toByteArray();
                String result = new String(data);
                result = "<" + result;

                System.out.println("Client: XML has been received from Server.\n");

                Jaxb jaxb = new Jaxb();

                do
                {
                    System.out.println("\nChose menu option: \n");
                    System.out.println("0. Exit.");
                    System.out.println("1. Transform XML to POJO and print data.");
                    System.out.println("2. Transform POJO to XML and print data.");

                    Scanner scanner = new Scanner(System.in);
                    String Option = scanner.nextLine();

                    switch (Option)
                    {
                        case "1":
                        {
                            school = jaxb.unmarshalll(result, true);
                            System.out.println(school);
                            System.out.println("Success");
                            break;
                        }
                        case "2":
                        {
                            if (school != null)
                            {
                                String xmlContent = jaxb.Marshall(school);
                                System.out.println(xmlContent);
                                System.out.println("Success");
                                break;
                            }
                            else
                            {
                                System.out.println("There is no data in server.");
                            }
                            break;
                        }
                        case "0":
                        {
                            stopClientServer();
                            System.exit(0);
                        }
                    }
                }
                while (true);
            }
            stopClientServer();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            stopClientServer();
        }
    }

    private void stopClientServer()
    {
        try
        {
            printWriter.close();
            bufferedReader.close();
            clientSocket.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            System.out.println("exited");
        }
    }

}