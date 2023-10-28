FROM openjdk:21
ADD target/DS2023_30641_Tulbure_Claudiu_Marcel_1_UserManagement-0.0.1-SNAPSHOT.jar user-management.jar
ENTRYPOINT ["java", "-jar", "/user-management.jar"]