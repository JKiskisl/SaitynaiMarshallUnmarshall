package Kiskis;

import java.net.*;
import java.io.*;



public class Server extends Thread
{
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    /**
     *
     * @param port set port
     */
    public void setPort(int port)
    {
        this.port=port;
    }

    /**
     *  runserver
     */
    public void run()
    {
        try
        {
            runServer();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @throws Exception if error while running
     */
    public void runServer() throws Exception
    {
        serverSocket = new ServerSocket(port);
        System.out.println("Running");
        clientSocket=serverSocket.accept();

        printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        printWriter.println("Local client server");
        String resp = bufferedReader.readLine();
        System.out.println("Local server got " + resp + " from client");

        SendXML();
    }

    public void SendXML()
    {
        try
        {
            int temp = 0;
            System.out.println("Sending xml to client");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(Server.class.getResourceAsStream("/Students.xml"));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream((clientSocket.getOutputStream()));
            byte[] bytes = new byte[8192];

            if ((temp = bufferedInputStream.read(bytes)) != -1)
            {
                bufferedOutputStream.write(bytes,0,temp);
            }
            bufferedOutputStream.flush();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            stopServer();
        }
    }

    public void stopServer()
    {
        try
        {
            printWriter.close();
            bufferedReader.close();
            clientSocket.close();
            serverSocket.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception
    {
        Server server = new Server();
        server.setPort(1001);
        server.start();
        server.join();
    }
}
