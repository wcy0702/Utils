package com.wcy.util;

import java.net.*;
import java.io.*;

/**
 * 
 * ����������<br />
 * �ͻ���SOCKETӦ�ó������������SOCKETӦ�ó�������̺����ƣ����Ĳ�����ڣ�<br />
 * 1����������SOCKETӦ�ó�����Ҫ�������������տͻ��˵����ӣ����ͻ��˵�SOCKET Ӧ�ó��������ڳ�����������˽�������
 * 
 * 2���ͻ���SOCKETӦ�ó�������Ϣָ�����������ˣ������շ������������صĽ����
 * ����������SOCEKTӦ�ó�������ָ���߼�����������������Ϣ�������ͻ���
 * 
 * �����Ŀͻ���Ӧ���У�<br />
 * CHAT�ͻ��ˣ�FTP�ͻ��ˣ�POP3�ͻ��ˣ�SMTP�ͻ��˺�TELNET�ͻ���<br />
 * 
 * �����ͻ���SOCKETӦ�ó���Ĳ���������£�
 * 
 * 1�������ͻ���SOCKET���ڽ���ʱ��ָ�������ӷ������˵��������ƣ���IP����INTERNET ͨ�Ŷ˿� <br />
 * 
 * 2�������ض���Ϣ��ָ������������
 * 
 * 3�����շ������˷��ص�ִ�н���������Ϣ�������ض���ʽ��ʾ������HTTPͨ��Э���ͨ�� HTML��ʾ
 * 
 * 4�����ͻ��˲���������˵Ĵ���ʱ����ر�SOCEKTͨ������
 * 
 * 
 * @author Administrator
 * @Date Jul 19, 2008
 * @Time 9:53:47 AM
 * @version 1.0
 * 
 */
public class simpleClient {

	private static Socket socket;

	public static void main(String[] args) throws Exception {
		String host;
		int port;
//		if (args.length < 2) {
//			System.out
//					.println("Usage:java simpleClient [remote IP/Host] [10001]");
//			System.exit(1);
//		}
		host = "127.0.0.1";
		port = Integer.parseInt("10001");
		connectServer(host, port);
	}

	/**
	 * �������������ӷ�����
	 * 
	 * @param host
	 *            �������˵��������ƻ���IP��ַ
	 * @param port
	 *            ��������ͨ�Ŷ˿�
	 */
	public static void connectServer(String host, int port) {
		try {
			socket = new Socket(InetAddress.getByName(host), port);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			@SuppressWarnings("unused")
			DataOutputStream out = new DataOutputStream(socket
					.getOutputStream());// ����ͨ��ͨ��

			byte[] inByte = new byte[1024];
			in.read(inByte);
			String response = new String(inByte, 0, inByte.length);
			System.out.println("Message from server: ");
			System.out.println(response.trim());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

	    			