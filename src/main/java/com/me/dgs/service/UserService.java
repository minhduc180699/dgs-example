package com.me.dgs.service;

import com.me.dgs.codegen.types.User;
import com.me.dgs.config.NebulaSessionWrapper;
import com.vesoft.nebula.client.graph.data.ResultSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserService {
    private final NebulaSessionWrapper session;
    // CREATE: Thêm Vertex
//    public void createPlayer(String playerId, String name, int age) {
//        String ngql = String.format("INSERT VERTEX player(name, age) VALUES \"%s\":(\"%s\", %d)",
//                playerId, name, age);
//        var result = session.execute(ngql);
//        result.
//    }
//
//    // CREATE: Thêm Edge
//    public void createFollow(String player1Id, String player2Id, int degree) {
//        String ngql = String.format("INSERT EDGE follow(degree) VALUES \"%s\"->\"%s\":(%d)",
//                player1Id, player2Id, degree);
//        execute(ngql);
//    }

//    // READ: Lấy thông tin User
//    public User getUser(String id) {
//        String ngql = String.format("FETCH PROP ON user \"%s\" YIELD vertex as node", playerId);
//        try {
//            ResultSet result = session.execute(ngql);
//            if (result.isSucceeded()) {
//                System.out.println(result.getRows());
//            } else {
//                System.err.println("Query failed: " + result.getErrorMessage());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    // UPDATE: Cập nhật tuổi của Player
//    public void updatePlayerAge(String playerId, int newAge) {
//        String ngql = String.format("UPDATE VERTEX ON player \"%s\" SET age = %d", playerId, newAge);
//        execute(ngql);
//    }
//
//    // DELETE: Xóa Edge Follow
//    public void deleteFollow(String player1Id, String player2Id) {
//        String ngql = String.format("DELETE EDGE follow \"%s\"->\"%s\"", player1Id, player2Id);
//        execute(ngql);
//    }
//
//    // DELETE: Xóa Vertex Player
//    public void deletePlayer(String playerId) {
//        String ngql = String.format("DELETE VERTEX \"%s\"", playerId);
//        execute(ngql);
//    }

}