FROM azul/zulu-openjdk:8
MAINTAINER Orlando L Otero <ootero@asimio.net>, https://bitbucket.org/asimio/springboot-jersey-swagger-docker-example.git

VOLUME /tmp

# Update Ubuntu
RUN \
  bash -c 'apt-get -qq update && apt-get -y upgrade && apt-get -y autoclean && apt-get -y autoremove' && \
  bash -c 'DEBIAN_FRONTEND=noninteractive apt-get install -y curl wget tar'

ENV USER_NAME jerseyexample
ENV APP_HOME /opt/poc-api/$USER_NAME

RUN \
  useradd -ms /bin/bash $USER_NAME && \
  mkdir -p $APP_HOME

ADD springboot-jersey-swagger-docker.jar ${APP_HOME}/springboot-jersey-swagger-docker.jar
RUN \
  chown $USER_NAME $APP_HOME/springboot-jersey-swagger-docker.jar && \
  bash -c 'touch ${APP_HOME}/springboot-jersey-swagger-docker.jar'

ENV JAVA_TOOL_OPTIONS "-Xms128M -Xmx128M -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom"

USER $USER_NAME
WORKDIR $APP_HOME
ENTRYPOINT ["java", "-jar", "springboot-jersey-swagger-docker.jar"]

# Run as:
# docker run -idt -p 8701:8701 -e appPort=8701 asimio/springboot-jersey-swagger-docker:latest
