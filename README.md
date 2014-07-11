# What is the SBT?
Deploy, Dependency, generate document, run Test ... 등등을 처리한다. 

### 빌드툴 비교
* Ant 
Make와 같은 종류라고 할수 있다. 자바 프로젝트를 위해서 만들어졌음.
빌드 스크립트를 xml로 작성되며 큰 프로젝트를 할경우 빌트 스크립트 매지닝이 어렵다.

* Maven 
2004년 릴리즈됨.
Ant의 몇가지 디자인 문제점을 개선함. 무엇보다도 중앙 레파지토리 Maven central처리가 가장큰 장점이다.
스칼라 플로그인을 통해서 빌드가 가능하지만, 속도가 다소 느린것으로 알고있음.
Xml 베이스

* Gradle
가장 핫한 빌드툴이라 할수 있다. 원래는 Groovy를 위해서 나온 빌드툴 하지만, 자바, 스칼라등 가능함.. 플로그인을 통해서.
Ant, Maven의 디자인을 적용했다고 할수 있음..
2012년 릴리즈됨, DSL 베이스 

* Sbt
Ivy를 이용한 Dependencies 처리 가능, test속도가 빠름, 큰프로젝트를 모듈별 병렬처리 가능, 배포등등 가능함.. 
Maven에 비해서 빌드 스크립트 코딩이 간단함. 
스칼라 언어로 빌드가능함.

# SBT Requirements Install

```
1. JVM 1.5 over
2. brew install scala
3. brew install sbt
```

# Sbt기반 프로젝트 만들자.

```
lib/
   <unmanaged jars go here>
src/
   main/
      resources/
         <files to include in project jar go here>
      scala/
         <main scala source files go here>
      java/
         <main Java source files go here>
   test/
      resources/
         <files to be included in the project test jar go here>
      scala/
         <scala test source files go here>
      java/
         <Java test source files go here>
project/
         <project build scala files go here>




  1. Create a helloProject directory
  2. cd to helloWorld
  3. mkdir lib
  4. mkdir -p src/main/resources
  5. mkdir src/main/scala
  6. mkdir src/main/java
  7. mkdir -p src/test/resources
  8. mkdir src/test/scala
  9. mkdir src/test/java
  10. mkdir project
```

위의 구성으로 만듬.

# Managed Dependencies
libraryDependencies += groupID % artifactID % revision

# 멀티 프로젝트 작성
공식 문서 : http://www.scala-sbt.org/release/tutorial/Multi-Project.html

* Sharing settings
다른 프로젝트사이의 세팅을 재사용을 쉽게 할수있는 방법? ThisBuild scope를 만들어서 globlly하게 적용한다. 
아래와 같이 root 프로젝트에서 설정하면 그하위 프로젝트 또한 적용된다. 

```
calaVersion in ThisBuild := "2.10.0"
```

* Aggregation
완벽히 독립적으로 빌드프로젝트를 만들기 위해 사용함. 
서브 프로젝트에서 aggregate를 사용하지 않을때 아래 옵션 사용함. 

```
aggregate in update := false
```


* Classpath dependencies
dependsOn 메소드 call을  통해서 dependency를 처리한다. 
아래와 같이 hello-foo프로젝트는 hello-bar프로젝트를 필요로 한다는것을 명시함.
```
lazy val foo = Project(id = "hello-foo",
                       base = file("foo")) dependsOn(bar)
```


```

import sbt._
import Keys._

object MyBuild extends Build {

     lazy val root = project.in(file(“.”)).aggregates(core, util)

     lazy val core = project
          .settings(
               libraryDependencies += “org.fluentd” % “fluent-logger” % “0.2.10”
          )

     lazy val util = project


object - scala singleton class 
lazy val - 변수정의는 처음 사용될때 처리된다.
디렉토리 모듈명시하는 방법 : project.in(file(“...”)) 이와 같이 작성한다. 

```


# SBT Settings
SBT 세팅 프로젝트는 3개의 파트로 이루어졌다.

* scope
* name
* value

SettingKey[T] trait 를 통해서 API를 제공해주고 있음.

# SBT Scope
scope axes에는 3가지가 있다.

* Projects
* Configurations
* Tasks

# SBT Keys
scope안에 세팅값으로 저장되는 값이다. AttributeKey[T]



* Project dependencies 
멀티 프로젝트에서 aggregate를 통해서 편리하게 세팅이 가능하다. 
몇몇 프로젝트가 다른 프로젝트 코드의 depends on한다면 dependOn을 사용한다. 
lazy val core = project.settings(….).dependOn(util)

lazy val 을 사용한다. 
```

# Plugin 설정 및 사용
* Sbt-assembly
* Sbt-pack
* Sbt-dependency-graph


# PredictionIO를 이용한 설정한 사례 설명

# 테스트 


