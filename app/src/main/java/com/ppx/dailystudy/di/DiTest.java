package com.ppx.dailystudy.di;

class DiTest {


   public void test(){
      //DaggerApplicationComponent就是dagger通过注解去依赖关系图获取到的对象实例
      ApplicationComponent applicationComponent = DaggerApplicationComponent.create();
   }
}
