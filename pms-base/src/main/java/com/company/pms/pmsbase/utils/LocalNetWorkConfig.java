package com.company.pms.pmsbase.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @Author zcf
 * @Create 2018/9/21 22:42
 * @Desc
 */
public class LocalNetWorkConfig {
    /**
     * 获取本地IP列表（针对多网卡情况）
     * @return
     */
    public static Map<String,Object> getLocalInetMac() {
        Map<String, Object> ipMacInfo = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while(networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while(inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    ipMacInfo = pickInetAddress(inetAddress, networkInterface);
                    if(ipMacInfo != null) {
                        return ipMacInfo;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, Object> pickInetAddress(InetAddress inetAddress, NetworkInterface networkInterface){
        try {
            String name = networkInterface.getDisplayName();
            if(name.contains("Adapter") || name.contains("Virtual") || name.contains("VMnet") || name.contains("#")){
                return null;
            }
            // 判断是否是虚拟接口（子接口， 及WIFI链接），是否支持组播
            if(networkInterface.isVirtual() || !networkInterface.supportsMulticast() || inetAddress.isLoopbackAddress()) {
                return null;
            }
            // 判断IP是否是否在使用, 是否为本地IP
            // 本地IP一种是指本地区IP地址、一种是只本地局域网的本地IP地址局域网IP一般为192.168.*.*,127.*.*.*这样
            if(networkInterface.isUp() || inetAddress.isSiteLocalAddress()) {
                Formatter formatter = new Formatter();
                String sMac = null;
                byte[] macBuf = networkInterface.getHardwareAddress();
                for(int i = 0; i<macBuf.length; i++) {
                    sMac = formatter.format(Locale.getDefault(), "%02x%s", macBuf[i], (i < macBuf.length - 1) ? "-" : "").toString();
                }
                formatter.close();
                Map<String, Object> ipMacInfo = new HashMap<String, Object>();
                ipMacInfo.put("hostname", inetAddress.getHostName()); // 系统当前名称
                ipMacInfo.put("ip", inetAddress.getHostAddress()); // IP地址
                ipMacInfo.put("ipnet", inetAddressTypeName(inetAddress)); // 网络类型
                ipMacInfo.put("os", System.getProperty("os.name")); //系统名称
                ipMacInfo.put("mac", sMac.toUpperCase()); //mac地址
                ipMacInfo.put("cpu-arch", System.getProperty("os.arch")); // CPU架构
                ipMacInfo.put("network-arch", networkInterface.getDisplayName());
                return ipMacInfo;
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String inetAddressTypeName(InetAddress inetAddress) {
        return (inetAddress instanceof Inet4Address) ? "ipv4" : "ipv6";
    }

    /**
     * 通过三方网站http://ip138.com/   -->   http://2018.ip138.com/ic.asp
     * @return
     */
    public static Map<String, String> getOpenNetWorkIp() {
        try {
            // 先从http://ip138.com/将显示IP的<iframe> 提取出来
            URLConnection openConnection = new URL("http://ip138.com/").openConnection();
            InputStream is = (InputStream) openConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("GBK")));
            StringBuilder sb = new StringBuilder();
            String str = null;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            String iframeSrc = sb.toString().toLowerCase(Locale.getDefault());
            String startSrc = "<iframe src=\"";
            String endSrc = "\" rel=\"nofollow\" frameborder=\"0\" scrolling=\"no\"></iframe>";
            iframeSrc = iframeSrc.substring(iframeSrc.indexOf(startSrc) + startSrc.length(), iframeSrc.indexOf(endSrc));

            // 读取 iframeSrc 链接中的内容
            openConnection = new URL(iframeSrc).openConnection();
            is = (InputStream) openConnection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, Charset.forName("GBK")));
            sb = new StringBuilder();
            str = null;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            String htmlSrc = sb.toString().toLowerCase(Locale.getDefault());
            String startTag = "<center>";
            String endTag = "</center>";
            // 得到<center></center>之间的内容
            htmlSrc = htmlSrc.substring(htmlSrc.indexOf(startTag) + startTag.length(), htmlSrc.lastIndexOf(endTag));
            String openIp = htmlSrc.substring(htmlSrc.indexOf("[") + 1, htmlSrc.lastIndexOf("]"));
            String provider = htmlSrc.substring(htmlSrc.lastIndexOf("：") + 1);
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("openIp", openIp);
            resultMap.put("provider", provider);
            br.close();
            return resultMap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检测http网络连接是否正常
     * @param urlStr
     * @return
     */
    private static boolean httpIsAvaliable(String urlStr) {
        URL url = null;
        InputStream in = null;
        try {
            url = new URL(urlStr);
            URLConnection connection = url.openConnection();
            connection.connect();
            in = connection.getInputStream();
            if(in != null && in.read() >= 0) {
                return true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            }catch (IOException e){

            }
        }
        return false;
    }

    /**
     * 检测 ip:port 网络访问是否正常
     * @param hostname
     * @param port
     * @return
     */
    private static boolean socketIsAvaiable(String hostname, int port) {
        Socket socket= null;
        try{
            socket = new Socket();
            socket.setKeepAlive(true);
            socket.setTcpNoDelay(true);
            socket.connect(new InetSocketAddress(hostname, port));
            if(!socket.isClosed() && socket.isConnected() && !socket.isInputShutdown() && !socket.isOutputShutdown()) {
                return true;
            }
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}