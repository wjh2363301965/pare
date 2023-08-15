package com.jianhaoweb.test;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

/**
 * @Auther:剑豪
 * @Date:2023/8/15
 * @VERSON:1.8
 */
public class FTPTest {
    public static void main(String[] args) {
        String server = "172.20.52.135";
        int port = 21;
        String user = "ftpuser";
        String password = "sailing";

        FTPClient ftpClient = new FTPClient();
        try {
            // 连接FTP服务器
            ftpClient.connect(server, port);
            // 登录
            if (ftpClient.login(user, password)) {
                System.out.println("链接成功");
            } else {
                System.out.println("登录失败");
            }

            // 设置主动模式，可以取消下一行的注释
            // ftpClient.enterLocalActiveMode();

            // 设置被动模式，可以取消下一行的注释
            // ftpClient.enterLocalPassiveMode();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}