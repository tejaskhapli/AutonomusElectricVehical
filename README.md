Step by Step instructions to build, deploy and use this service.

IDE Used: Spring Tool Suite 4

1. How to Import Project(In STS 4).
	- Download the zip file : AutonomusElectricVehical.zip.
	- Uncompress the AutonomusElectricVehical.zip file.
	- Open Spring Tool Suite IDE.
	- Click : File -> Import -> Existing Maven Projects -> Next.
	- Specify Root Directory till the /AutonomusElectricVehical Directory.
	- Click Finish.
	- The project will be imported to the STS 4 IDE.

2. How to Build the Project
	- Right Click on Project AutonomusElectricVehical / auto-elect-veh-service
	- Navigate : Run as maven install.
	- This will build the project ans create a jar in /target directory.
	- This project can also be built using following maven command:
		- mvn clean install.
	- [the build files in target directory are already included in zip if required].

3. How to Deploy the project using Docker
	- Create Docker Image using following Command
		- docker build -f Dockerfile -t autonomous-electric-vehical .
	- Once docker image is created, it can be seen and verified using following command:
		- docker images
	- Open Docker Desktop and Run the Docker image created for this application in above step.
	- All the necessary steps have been included in Dockerfile for running the image in port 8080
	- [Docker Image can also be run using below command through command line interface(terminal)]
		- docker run -d -p 8080:8080 autonomous-electric-vehical
	- Once the docker image is running, run the application using below command:
		- To Run the Application REST API following details:
			- URL : localhost:8080/process
			- Http Method : POST
			- Body : { "vin": "W1K2062161F0017", "source": "Home", "destination": "Zoo" }
