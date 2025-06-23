FROM openjdk:21-ea-1-jdk

WORKDIR /app
COPY target/api-giret-resources-1.0.0.jar app.jar
COPY Wallet_DQEVECTR9GSE4KR1 /app/oracle_wallet
EXPOSE 8081

CMD [ "java", "-jar", "app.jar" ]
