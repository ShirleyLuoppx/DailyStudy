package com.ppx.dailystudy.di;

class UserRemoteDataResource {
    private String remoteName;

    public String getRemoteName() {
        return remoteName;
    }

    public void setRemoteName(String remoteName) {
        this.remoteName = remoteName;
    }

    public UserRemoteDataResource(String remoteName) {
        this.remoteName = remoteName;
    }

    public UserRemoteDataResource() {
    }
}
