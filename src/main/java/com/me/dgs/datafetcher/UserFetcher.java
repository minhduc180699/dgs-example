package com.me.dgs.datafetcher;

import com.me.dgs.codegen.types.User;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class UserFetcher {
    @DgsQuery
    public List<User> getUsers(@InputArgument String filter) {

    }

}
