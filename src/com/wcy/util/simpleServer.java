package com.wcy.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * ����������<br />
 * socket������Ӧ�ó���ĺ��ģ��ڷ������˻�ͻ�������Ӧ�ó����У�socket��Ϊ����ȱ�ٵ�Ҫ��
 * �ڷ�����������Ӧ���У�FTP��������MAIL��������SMTP��POP3��IMAP4Э�飩��WEB��HTTPЭ�飩��
 * 
 * ������������SOCKET��Ӧ�ó��������£�<br />
 * 
 * 1�������������˵�SOCKET�������Դ������ͻ��˵��������� <br />
 * 
 * 2��������������⵽���Կͻ��˵���������ʱ����մ����󲢽����ͻ��˵�SOCKET����SOCEKT����Ϊ
 * �ͻ������Ӽ����������ͼ��������ݵ����ݣ���������ɷ���������ͻ��˵�SOCKETͨ������
 * 
 * 3���������Կͻ��˵���Ϣ��һ���Ϊ���󣬿���Ϊ�ͻ��˵�ָ����������HTTPͨ��Э���URL����<br />
 * ��FTPͨ��Э���FTP�����GET��PUT����;<br />
 * 
 * 4�����ݿͻ��˴�����������������辭�������߼�����֮�󣬷��ͻ����Ӧ���д�����������Ϣ��
 * �ͻ�����HTTP�������뷢�ͻ�HTML��ҳ���ݣ���FTP���������ͻ�FTPָ��Ľ�� <br />
 * 
 * 5��������������ݻ�����Ĵ���֮�󣬱�ر�SOCKETͨ������
 * 
 * @author Administrator
 * @Date Jul 19, 2008
 * @Time 9:45:54 AM
 * @version 1.0
 */
public class simpleServer {

	private static ServerSocket serverSocket;

	public static void main(String[] args) throws Exception {
		int port;

//		if (args.length == 0) {
//			System.out.println("Usage:java simpleServer [10001]");
//			System.exit(1);
//		}
		port = Integer.parseInt("10001");
		startServer(port);
	}

	/**
	 * ��������������������
	 * 
	 * @param port
	 *            �������˿�
	 * @throws Exception
	 */
	public static void startServer(int port) throws Exception {
		try {
			serverSocket = new ServerSocket(port);
			Thread thread = new Thread(new ListenClient(serverSocket));
			thread.start();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

/**
 * 
 * ������������������
 * 
 * @author FangHewei
 * @Date Jul 19, 2008
 * @Time 9:57:53 AM
 * @version 1.0
 * 
 */
class ListenClient implements Runnable {
	private ServerSocket serverSocket;
	private Socket clientSocket;

	DataInputStream in;
	DataOutputStream out;

	public ListenClient(ServerSocket serverSocket) throws Exception {
		this.serverSocket = serverSocket;
	}

	@SuppressWarnings("static-access")
	public void run() {
		try {
			while (true) {
				clientSocket = serverSocket.accept();
				System.out.println("Connection from "
						+ clientSocket.getInetAddress().getHostAddress());
				in = new DataInputStream(clientSocket.getInputStream());
				out = new DataOutputStream(clientSocket.getOutputStream());

				String lineSep = System.getProperty("line.separator");// �зָ��������س�����
				@SuppressWarnings("unused")
				InetAddress addr = serverSocket.getInetAddress().getLocalHost();

				String outData = "Server Information: " + lineSep
						+ "  Local Host: "
						+ serverSocket.getInetAddress().getLocalHost()
						+ lineSep + " port  :" + serverSocket.getLocalPort();
				byte[] outByte = outData.getBytes();
				out.write(outByte, 0, outByte.length);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
};

	    			