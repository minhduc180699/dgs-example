package com.me.dgs.config;

import com.vesoft.nebula.client.graph.NebulaPoolConfig;
import com.vesoft.nebula.client.graph.NebulaSession;
import com.vesoft.nebula.client.graph.data.HostAddress;
import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.exception.AuthFailedException;
import com.vesoft.nebula.client.graph.exception.ClientServerIncompatibleException;
import com.vesoft.nebula.client.graph.exception.IOErrorException;
import com.vesoft.nebula.client.graph.exception.NotValidConnectionException;
import com.vesoft.nebula.client.graph.net.NebulaPool;
import com.vesoft.nebula.client.graph.net.Session;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class NebulaConfig {

    @Value("${nebula.addresses}")
    private String addresses;

    @Value("${nebula.username}")
    private String username;

    @Value("${nebula.password}")
    private String password;

    @Value("${nebula.space}")
    private String spaceName;

    @Value("${nebula.pool.max-conn-size}")
    private int maxConnSize;
    @Bean
    public NebulaSessionWrapper nebulaSessionWrapper() throws Exception {
        NebulaSessionWrapper wrapper = new NebulaSessionWrapper(addresses, username, password, spaceName, maxConnSize);
        wrapper.init();
        return wrapper;
    }

}