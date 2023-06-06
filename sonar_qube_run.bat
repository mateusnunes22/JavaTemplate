mvn clean verify sonar:sonar \
  -Dsonar.projectKey=java \
  -Dsonar.projectName='java' \
  -Dsonar.host.url=http://localhost:9099 \
  -Dsonar.token=sqp_3e97a90c4a4607b97b7f3265f754c94b2c8fba5d