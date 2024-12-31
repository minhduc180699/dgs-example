package com.me.dgs.config;
import com.vesoft.nebula.client.graph.NebulaPoolConfig;
import com.vesoft.nebula.client.graph.data.HostAddress;
import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.net.NebulaPool;
import com.vesoft.nebula.client.graph.net.Session;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class NebulaSessionWrapper {
    private static final Logger log = LoggerFactory.getLogger(NebulaSessionWrapper.class);
    private final String addresses;
    private final String username;
    private final String password;
    private final String spaceName;
    private final int maxConnSize;
    @Getter
    private Session session;
    private NebulaPool pool;

    public NebulaSessionWrapper(String addresses, String username, String password, String spaceName, int maxConnSize) {
        this.addresses = addresses;
        this.username = username;
        this.password = password;
        this.spaceName = spaceName;
        this.maxConnSize = maxConnSize;
    }

    public void init() throws Exception {
        NebulaPoolConfig nebulaPoolConfig = new NebulaPoolConfig();
        nebulaPoolConfig.setMaxConnSize(maxConnSize);
        List<HostAddress> hostAddresses = Arrays.stream(addresses.split(","))
                .map(address -> {
                    String[] parts = address.split(":");
                    return new HostAddress(parts[0], Integer.parseInt(parts[1]));
                })
                .toList();
        pool = new NebulaPool();
        pool.init(hostAddresses, nebulaPoolConfig);
        session = pool.getSession(username, password, true);
        session.execute("USE " + spaceName + ";");
        log.info("Nebula session initialized for space: {}", spaceName);
    }
    public ResultSet execute(String ngql) throws Exception{
        return session.execute(ngql);
    }

    @PreDestroy
    public void close() {
        if (session != null) {
            session.release();
            log.info("Nebula session released");
        }
        if (pool != null) {
            pool.close();
            log.info("Nebula pool closed");
        }
    }
}
