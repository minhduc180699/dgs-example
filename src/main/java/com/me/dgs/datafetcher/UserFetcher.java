package com.me.dgs.datafetcher;

import com.me.dgs.codegen.DgsConstants;
import com.me.dgs.codegen.types.*;
import com.me.dgs.config.NebulaSessionWrapper;
import com.netflix.graphql.dgs.*;
import com.vesoft.nebula.client.graph.data.ResultSet;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.me.dgs.constant.UserQuery.*;

@DgsComponent
@RequiredArgsConstructor
public class UserFetcher {
    private final NebulaSessionWrapper session;
    @DgsQuery
    public User getUser(@InputArgument String id) {
        String ngql = String.format(GET_USER_BY_ID, id);
        try {
            ResultSet result = session.execute(ngql);
            if (result.isSucceeded()) {
                User user = new User();
                ResultSet.Record row = result.rowValues(0);
                user.setId(row.get(0).asString());
                user.setName(row.get(1).asString());
                user.setAge((int) row.get(2).asLong());
                user.setLocation(row.get(3).asString());
                return user;
            } else {
                System.err.println("Query failed: " + result.getErrorMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @DgsData(parentType = DgsConstants.USER.TYPE_NAME, field = DgsConstants.USER.Friends)
    public List<User> friends(DataFetchingEnvironment env) {
        User currentUser = env.getSource();
        String ngql = String.format(FRIENDS, currentUser.getId(), currentUser.getId());
        try {
            ResultSet result = session.execute(ngql);
            if (result.isSucceeded()) {
                List<User> users = new ArrayList<>();
                for (int i = 0; i < result.rowsSize(); i++) {
                    User user = new User();
                    ResultSet.Record row = result.rowValues(i);
                    user.setId(row.get(0).asString());
                    user.setName(row.get(1).asString());
                    user.setAge((int) row.get(2).asLong());
                    user.setLocation(row.get(3).asString());
                    users.add(user);
                }
                return users;
            } else {
                System.err.println("Query failed: " + result.getErrorMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }
    @DgsQuery
    public List<User> getUsers(@InputArgument String filter) {
        String ngql = String.format(GET_USER_BY_NAME, filter);
        try {
            ResultSet result = session.execute(ngql);
            if (result.isSucceeded()) {
                List<User> users = new ArrayList<>();
                for (int i = 0; i < result.rowsSize(); i++) {
                    User user = new User();
                    ResultSet.Record row = result.rowValues(i);
                    user.setId(row.get(0).asString());
                    user.setName(row.get(1).asString());
                    user.setAge((int) row.get(2).asLong());
                    user.setLocation(row.get(3).asString());
                    users.add(user);
                }
                return users;
            } else {
                System.err.println("Query failed: " + result.getErrorMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }
    @DgsMutation
    public Response createUser(@InputArgument InputUser inputUser) {
        String id = UUID.randomUUID().toString();
        String ngql = String.format(INSERT_USER, id, inputUser.getName(),
                inputUser.getAge(), inputUser.getLocation());
        try {
            ResultSet result = session.execute(ngql);
            if (result.isSucceeded()) {
                return Response.newBuilder().id(id).message("success").build();
            } else {
                System.err.println("Query failed: " + result.getErrorMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  Response.newBuilder().id(id).message("failed").build();
    }
    @DgsMutation
    public Response updateUser(@InputArgument InputModifiedUser inputUser) {
        String id = inputUser.getId();
        String ngql = String.format(UPDATE_USER, id, inputUser.getName(),
                inputUser.getAge(), inputUser.getLocation());
        try {
            ResultSet result = session.execute(ngql);
            if (result.isSucceeded()) {
                return  Response.newBuilder().id(id).message("success").build();
            } else {
                System.err.println("Query failed: " + result.getErrorMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  Response.newBuilder().id(id).message("failed").build();
    }
    @DgsMutation
    public Response deleteUser(@InputArgument String id) {
        String ngql = String.format(DELETE_USER, id);
        try {
            ResultSet result = session.execute(ngql);
            if (result.isSucceeded()) {
                return  Response.newBuilder().id(id).message("success").build();
            } else {
                System.err.println("Query failed: " + result.getErrorMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  Response.newBuilder().id(id).message("failed").build();
    }

    @DgsMutation
    public Response addFriend(@InputArgument InputAddFriend input) {
        String ngql = String.format(INSERT_ADD_FRIEND,
                input.getAdder(),
                input.getAddee(),
                LocalDateTime.now().toString(), 0.5f );
        try {
            ResultSet result = session.execute(ngql);
            if (result.isSucceeded()) {
                return  Response.newBuilder().message("success").build();
            } else {
                System.err.println("Query failed: " + result.getErrorMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  Response.newBuilder().message("failed").build();
    }
}
