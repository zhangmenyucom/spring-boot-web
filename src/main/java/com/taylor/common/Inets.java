package com.taylor.common;


import java.io.Closeable;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author jearton
 * @since 2017/2/7
 */
public  class Inets implements Closeable {

    public static final String IP_SYSTEM_KEY = "HOST_IP";

    private static final String FALLBACK_IP = "0.0.0.0";

    private static final Inet4Address FALLBACK_ADDRESS = _getInet4AddressByName(FALLBACK_IP, FALLBACK_IP);

    private static volatile Inet4Address localInet4Address;

    private static volatile String localHostAddress;

    private static volatile String localHostName;

    private static AtomicBoolean initialized = new AtomicBoolean(false);

    private static ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
        Thread thread = new Thread(r);
        thread.setName("qianbao-booster-inets");
        thread.setDaemon(true);
        return thread;
    });

    @Override
    public void close() {
        executor.shutdown();
    }

    public static int fetchLocalIpAsInt(int timeoutSeconds) throws Exception {
        Future<Integer> result = executor.submit((Callable<Integer>) Inets::fetchLocalIpAsInt);
        try {
            return result.get(timeoutSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new Exception(e);
        }
    }

    public static String fetchLocalIp(int timeoutSeconds) throws Exception {
        Future<String> result = executor.submit((Callable<String>) Inets::fetchLocalIp);
        try {
            return result.get(timeoutSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new Exception(e);
        }
    }

    /**
     * @return 将ip地址以整数的形式返回
     */
    public static int fetchLocalIpAsInt() {
        initialize();
        return ByteBuffer.wrap(localInet4Address.getAddress()).getInt();
    }

    public static String fetchLocalIp() {
        initialize();
        return localHostAddress;
    }

    public static String fetchLocalHostName() {
        initialize();
        return localHostName;
    }

    private static void initialize() {
        if (initialized.compareAndSet(false, true)) {
            localInet4Address = _getPriorLocalInet4Address();
            localHostAddress = localInet4Address.getHostAddress();
            localHostName = localInet4Address.getHostName();
            System.setProperty(IP_SYSTEM_KEY, localHostAddress);
        }
    }

    /**
     * @return 优先获取权重高的IPV4地址
     */
    private static Inet4Address _getPriorLocalInet4Address() {
        String ip = System.getProperty(IP_SYSTEM_KEY); // JVM argument: -DHOST_IP=1.2.3.4
        if (ip == null) {
            ip = System.getenv(IP_SYSTEM_KEY); // Environment variable: HOST_IP=1.2.3.4
        }
        if (ip != null) {
            try {
                return (Inet4Address) InetAddress.getByName(ip);
            } catch (Exception ignored) {
            }
        }

        List<Inet4Address> inet4Addresses = _getLocalInet4AddressList();
        Inet4Address local = FALLBACK_ADDRESS;
        int maxWeight = -1;
        for (Inet4Address inet4Address : inet4Addresses) {
            int weight = 0;
            if (inet4Address.isSiteLocalAddress()) {
                weight += 8;
            }
            try {
                if (inet4Address.isReachable(500)) {
                    weight += 6;
                }
            } catch (IOException ignored) {
            }
            if (inet4Address.isLinkLocalAddress()) {
                weight += 4;
            }
            if (inet4Address.isLoopbackAddress()) {
                weight += 2;
            }
            if (!inet4Address.getHostAddress().equals(inet4Address.getHostName())) { // 有独立的HostName
                weight += 1;
            }
            if (weight > maxWeight) {
                maxWeight = weight;
                local = inet4Address;
            }
        }
        return local;
    }


    /**
     * @return 遍历服务器所有IPV4地址
     */
    private static List<Inet4Address> _getLocalInet4AddressList() {
        List<Inet4Address> ipList = new ArrayList<>();
        Enumeration<NetworkInterface> allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException ignored) {
            return ipList;
        }
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = allNetInterfaces.nextElement();
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress ip = addresses.nextElement();
                if (ip instanceof Inet4Address) {
                    ipList.add((Inet4Address) ip);
                }
            }
        }
        return ipList;
    }

    private static Inet4Address _getInet4AddressByName(String ip, String fallbackIp) {
        try {
            return (Inet4Address) InetAddress.getByName(ip);
        } catch (Exception e) {
            try {
                return (Inet4Address) InetAddress.getByName(fallbackIp);
            } catch (Exception e1) {
                throw new IllegalArgumentException("Cannot find fallbackIp", e1);
            }
        }
    }
}
