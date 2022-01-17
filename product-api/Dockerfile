FROM gradle:7.0.0-jdk11
COPY --chown=gradle:gradle . .
WORKDIR .
RUN gradle build -x test --no-daemon
EXPOSE 9193
CMD ["gradle", "bootRun"]