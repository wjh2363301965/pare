package com.jianhaoweb.config;

import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

public class Tools {
    public static String generateServiceID(String moduleType){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer serviceID = new StringBuffer();
        serviceID.append(moduleType);
        serviceID.append(dateFormat.format(new Date()));
        return serviceID.toString();
    }

    /***
     * 获取UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }

    /***
     * pingIp
     * @param ip
     * @return
     */
    public static Boolean pingIp(String ip){
        Boolean result = false;
        try {
            int  timeOut =  3000 ;  //超时应该在3钞以上
            result = InetAddress.getByName(ip).isReachable(timeOut);     // 当返回值是true时，说明host是可用的，false则不可。
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 测试IP、端口是否可达
     * @param host
     * @param port
     * @return
     */
    public static boolean isHostConnectable(String host, int port) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port),3000);
        } catch (IOException e) {
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 对字符串md5加密(小写+字母)
     *
     * @param str 传入要加密的字符串
     * @return MD5加密后的字符串
     */
    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //文件上传工具类服务方法

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception{

        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static Boolean writeStringToFile(byte[] file, String filePath, String fileName){
        try {
            uploadFile(file, filePath, fileName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }




    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = Tools.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = Tools.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            return false;
        }
        // 删除当前目录
//        if (dirFile.delete()) {
//            System.out.println("删除目录" + dir + "成功！");
//            return true;
//        } else {
//            return false;
//        }
        return true;
    }

    public static List<String> getByteSize(double byteSize) {
        List<String> strs = new ArrayList<>();
        String data = "";
        String unit = "";
        if (byteSize > Math.pow(1024, 1) && byteSize < Math.pow(1024, 2)) {
            data = new BigDecimal(byteSize / Math.pow(1024, 1)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"";
            unit = "KB";
        }else if (byteSize > Math.pow(1024, 2) && byteSize < Math.pow(1024, 3)) {
            data = new BigDecimal(byteSize / Math.pow(1024, 2)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"";
            unit = "MB";
        } else if (byteSize > Math.pow(1024, 3) && byteSize < Math.pow(1024, 4)) {
            data = new BigDecimal(byteSize / Math.pow(1024, 3)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"";
            unit = "GB";
        } else if (byteSize > Math.pow(1024, 4) && byteSize < Math.pow(1024, 5)) {
            data = new BigDecimal(byteSize / Math.pow(1024, 4)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"";
            unit = "TB";
        } else if (byteSize > Math.pow(1024, 5) && byteSize < Math.pow(1024, 6)) {
            data = new BigDecimal(byteSize / Math.pow(1024, 5)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"";
            unit = "PB";
        } else if (byteSize > Math.pow(1024, 6) && byteSize < Math.pow(1024, 7)) {
            data = new BigDecimal(byteSize / Math.pow(1024, 6)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"";
            unit = "EB";
        } else if (byteSize > Math.pow(1024, 7) && byteSize < Math.pow(1024, 8)) {
            data = new BigDecimal(byteSize / Math.pow(1024, 7)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"";
            unit = "ZB";
        } else if (byteSize < 1024) {
            data = new BigDecimal(byteSize ).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"";
            unit = "B";
        } else {
            data = "数据超过数据大小ZB:" + byteSize;
        }
        strs.add(data);
        strs.add(unit);

        return strs;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String convertIpAddress(String ip){
        if(IPAddressUtil.isIPv6LiteralAddress(ip)){
            return "["+ip+"]";
        }
        return ip;
    }


}
