package com.me.dgs.constant;

import com.me.dgs.config.NebulaSessionWrapper;

public class UserQuery {
    
    public final static String GET_ALL_USER =
            "MATCH (v:user) " +
                    "RETURN id(v), v.user.name,v.user.age, v.user.age;";
    public final static String GET_USER_BY_ID =
            "FETCH PROP ON user \"%s\" " +
            "YIELD id(VERTEX), user.name, user.age, user.location";
    public final static String GET_USER_BY_NAME = 
            "MATCH (v:user) " +
            "WHERE v.user.name CONTAINS \"%s\"" +
            "RETURN id(v), v.user.name,v.user.age, v.user.location;";
    public final static String INSERT_USER =
            "INSERT VERTEX user(name, age, location) " +
            "VALUES \"%s\": (\"%s\",%d,\"%s\")";
    public final static String UPDATE_USER =
            "UPDATE VERTEX ON user \"%s\" " +
            "SET name = \"%s\", age = %d, location = \"%s\"";
    public final static String DELETE_USER = "DELETE VERTEX \"%s\"";

    public final static String INSERT_ADD_FRIEND =
            "INSERT EDGE add_friend(since, intimacy) " +
                    "VALUES \"%s\"->\"%s\": (datetime(\"%s\"), %f)";
    public final static String FRIENDS =
            "GO FROM \"%s\" OVER add_friend YIELD id($$) as f1 | " +
                    "GO FROM $-.f1 OVER add_friend WHERE id($$) == \"%s\" YIELD $-.f1 as f | " +
                    "FETCH PROP ON user $-.f YIELD id(vertex), user.name, user.age, user.location";
}
