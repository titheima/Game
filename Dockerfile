FROM openjdk:8-jre-alpine

ADD target/*.jar app.jar

ENV MONGO_HOST=mongo \
    MONGO_PORT=27017

EXPOSE 8080

CMD java \
    -jar \
    -server \
    /app.jar \
    --spring.data.mongodb.host=$MONGO_HOST \
    --spring.data.mongodb.port=$MONGO_PORT