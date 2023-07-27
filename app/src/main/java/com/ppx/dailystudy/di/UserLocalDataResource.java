package com.ppx.dailystudy.di;

class UserLocalDataResource {
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public UserLocalDataResource() {
   }

   public UserLocalDataResource(String name) {
      this.name = name;
   }
}
