mvn clean
mvn package
docker build -t nilare/sdypp-tp1-2-server:latest .
docker push nilare/sdypp-tp1-2-server:latest
docker stop sdypp-tp1-2-server
docker rm sdypp-tp1-2-server
docker run --name sdypp-tp1-2-server -p 12000:12000 nilare/sdypp-tp1-2-server:latest