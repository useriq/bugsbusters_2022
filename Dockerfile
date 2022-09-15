FROM gradle:jdk11

# Параметризуем имя пользователя, его Id и id группы
ARG USER_UID=1002
ARG USER_GID=1002
ARG USER=jenkins

RUN apt-get update && apt-get install tree

# Добавляем пользователя и группу
RUN groupadd --system --gid $USER_UID $USER && \
	useradd --system --gid $USER --uid $USER_GID --create-home $USER

RUN mkdir /buildGradle && \
    mkdir /buildGradle/.gradle &&\
    chown --recursive $USER:$USER /buildGradle

COPY build/classes/kotlin/main /mainClasses
COPY build/classes/kotlin/test /testClasses
COPY build/testRuntimeClasspath /testRuntimeClasspath
COPY src/main/resources /resources
COPY src/test/resources /testResources
COPY runner /buildGradle

WORKDIR /buildGradle

ENTRYPOINT ["gradle", "integrationTest"]

