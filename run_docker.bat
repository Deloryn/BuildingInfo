mvn clean package
docker build -t buildinginfo .
docker run -it -p 8080:8080 --rm -v "$PWD":/app -w /app buildinginfo java -jar target/io-project-architecture-1.0.0.jar
