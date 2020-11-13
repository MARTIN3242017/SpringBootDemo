sudo docker rm -f $(docker ps -a | grep " SpringBootDemo " | awk '{print $1}')
sudo docker rmi -f SpringBootDemo:latest
cd /usr/local/samh/SpringBootDemo
pwd
sudo cp /home/wankai/SpringBootDemo/SpringBootDemo-0.0.1-SNAPSHOT.jar .
sudo cp /home/wankai/SpringBootDemo/Dockerfile .
sudo chmod 777 SpringBootDemo-0.0.1-SNAPSHOT.jar
sudo docker build -t SpringBootDemo .
sudo docker run --name=SpringBootDemo -d -p 8994:8994 -v /home/wankai/SpringBootDemo/:/home/wankai/SpringBootDemo/ --env spring.profiles.active=test SpringBootDemo
echo 'Rebuild Success!'