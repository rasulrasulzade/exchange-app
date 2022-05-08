//run container
docker-compose up -d

mvn clean install

mvn spring-boot:run