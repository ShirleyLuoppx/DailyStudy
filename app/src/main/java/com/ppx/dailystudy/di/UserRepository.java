package com.ppx.dailystudy.di;

import javax.inject.Inject;

class UserRepository {

    private UserLocalDataResource userLocalDataResource;
    private UserRemoteDataResource userRemoteDataResource;

    @Inject
    //通过构造方法传参进来是 手动注入的方式...
    public UserRepository(UserLocalDataResource localDataResource, UserRemoteDataResource remoteDataResource) {
        userLocalDataResource = localDataResource;
        userRemoteDataResource = remoteDataResource;
    }
}
