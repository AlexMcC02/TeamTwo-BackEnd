FROM maven:latest

WORKDIR /code

COPY . /code

ARG DB_HOST
ARG DB_PASSWORD
ARG DB_USER
ARG DB_NAME

ENV DB_HOST ${DB_HOST}
ENV DB_PASSWORD ${DB_PASSWORD}
ENV DB_USER ${DB_USER}
ENV DB_NAME ${DB_NAME}

RUN mvn clean install -DskipTests=true

EXPOSE 8080

CMD ["java","-jar", "/src/main/java/org/kainos/ea/DropwizardWebServiceApplication.java", "server", "/code/config.yml"]