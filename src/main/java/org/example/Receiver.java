package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Receiver {


    public void run(int port, Main.CallbackInterface callbackInterface) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(port);
            byte[] receiveData = new byte[14];
            System.out.printf("Listening on udp:%s:%d%n",
                    InetAddress.getLocalHost().getHostAddress(), port);
            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);

            while (true) {
                serverSocket.receive(receivePacket);
                System.out.println("------------------------------------------------------------");
                System.out.println("RECEIVED: " + convertyByteArrayToHexString(receivePacket.getData()));
                int nodeNumber = receivePacket.getData()[0];
                System.out.println("Nodennumber:" + nodeNumber);
                int dataSequence = receivePacket.getData()[1];
                System.out.println("Sequence:" + dataSequence);
                short Data1 = convertToInt(new byte[]{receivePacket.getData()[2], receivePacket.getData()[3]});
                short Data2 = convertToInt(new byte[]{receivePacket.getData()[4], receivePacket.getData()[5]});
                short Data3 = convertToInt(new byte[]{receivePacket.getData()[6], receivePacket.getData()[7]});
                short Data4 = convertToInt(new byte[]{receivePacket.getData()[8], receivePacket.getData()[9]});
                short[] data = {Data1, Data2, Data3, Data4};
                callbackInterface.onReceive(dataSequence, nodeNumber, data);
            }
        } catch (IOException e) {
            System.out.println("Check your network-settings, maybe port is use?");
            throw new RuntimeException(e);
        }
        // should close serverSocket in finally block
    }


    public static String convertyByteArrayToHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            int decimal = (int) aByte & 0xff;               // bytes widen to int, need mask, prevent sign extension
            // get last 8 bits
            String hex = Integer.toHexString(decimal);
            if (hex.length() % 2 == 1) {                    // if half hex, pad with zero, e.g \t
                hex = "0" + hex;
            }
            result.append(hex);
        }
        return result.toString();
    }

    public static short convertToInt(byte[] bytes) {
        short buffer = 0;
        buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(buffer);
        return buffer;
    }


}