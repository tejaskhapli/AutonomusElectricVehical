FROM openjdk:11
ADD target/AutonomusElectricVehical.jar AutonomusElectricVehical.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "AutonomusElectricVehical.jar"]