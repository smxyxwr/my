/*import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import Utils.addAction;
public class Main
{
 
    public static void main(String[] args) throws IOException
    {
        Recv();
    }
     
    @SuppressWarnings("static-access")
	public static void Recv() throws IOException
    {
        System.out.println("���ܿ�ʼ......");
        DatagramSocket ds = new DatagramSocket(3845);
        while(true)
        {
            byte[]buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf,buf.length);
            ds.receive(dp);
            System.out.println(ds);
            String ip = dp.getAddress().getHostAddress();
            int port = dp.getPort();
            String text = new String(dp.getData(),0,dp.getLength());
            if(text.equals("exit"))
                {
                    System.out.println(ip+"�˳��Ự......");
                    break;
                }
            System.out.println(ip+":"+port+"---->me "+text);
            new addAction().insert(text);
        }
 
        ds.close();
    }
}*/

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import Utils.addAction;

/**
 * �������˳���
 * 
 * @author ccna_zhang
 *
 */
public class Main {

	public static void main(String[] args) {
		int port = 3845;  
		
		try {
			InetAddress address = InetAddress.getLocalHost();
			System.out.println(""+address);
			//����DatagramSocket����
			DatagramSocket socket = new DatagramSocket(port, address);
			while(true){
			
			byte[] buf = new byte[1024];  //����byte����
			DatagramPacket packet = new DatagramPacket(buf, buf.length);  //����DatagramPacket����
			
			socket.receive(packet);  //ͨ���׽��ֽ�������
			
			String getMsg = new String(buf, 0, packet.getLength());
			System.out.printf("�ͻ��˷��͵�����Ϊ��%x %x\n",buf[9],buf[10]);
			
			new addAction().insert(""+buf[9]+","+buf[10]);
			
			
			
			InetAddress clientAddress = packet.getAddress(); //��ÿͻ��˵�IP��ַ
			int clientPort = packet.getPort(); //��ÿͻ��˵Ķ˿ں�
			SocketAddress sendAddress = packet.getSocketAddress();
			String feedback = "OK\n";
			byte[] backbuf = feedback.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length, sendAddress); //��װ���ظ��ͻ��˵�����
			socket.send(sendPacket);  //ͨ���׽��ַ�������������
			}
			//socket.close();  //�ر��׽���
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
