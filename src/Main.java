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
        System.out.println("接受开始......");
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
                    System.out.println(ip+"退出会话......");
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
 * 服务器端程序
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
			//创建DatagramSocket对象
			DatagramSocket socket = new DatagramSocket(port, address);
			while(true){
			
			byte[] buf = new byte[1024];  //定义byte数组
			DatagramPacket packet = new DatagramPacket(buf, buf.length);  //创建DatagramPacket对象
			
			socket.receive(packet);  //通过套接字接收数据
			
			String getMsg = new String(buf, 0, packet.getLength());
			System.out.printf("客户端发送的数据为：%x %x\n",buf[9],buf[10]);
			
			new addAction().insert(""+buf[9]+","+buf[10]);
			
			
			
			InetAddress clientAddress = packet.getAddress(); //获得客户端的IP地址
			int clientPort = packet.getPort(); //获得客户端的端口号
			SocketAddress sendAddress = packet.getSocketAddress();
			String feedback = "OK\n";
			byte[] backbuf = feedback.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length, sendAddress); //封装返回给客户端的数据
			socket.send(sendPacket);  //通过套接字反馈服务器数据
			}
			//socket.close();  //关闭套接字
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
