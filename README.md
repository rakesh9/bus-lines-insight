# Bus Lines Insight

The Bus Lines Insight application finds out which bus lines have the most bus stops on their route, and lists the top 10 as clear text and also lists the names of every bus stop of the bus line that has the most stops.
The application extracts this information using Trafiklab's open API, which is available at http://www.trafiklab.se/api/sl-hallplatser-och-linjer-2

## Prerequisites

To run the Bus Lines Insight application, you'll need the following:

- Java 17 or higher installed on your machine
- Gradle 7.1 or higher installed on your machine

## Running the Application

To run the application, follow these steps:

1. Clone the application code from GitHub:
```sh
git clone https://github.com/rakesh9/bus-lines-insight.git  
```
2. Change into the project directory:
```sh
cd bus-lines-insight
```
3. Build the application using Gradle
```shell
./gradlew build
```
4. Run the application
```shell
java -jar build/libs/bus-lines-insight-0.0.1-SNAPSHOT.jar
```
The application will start up, and you should see output in the console indicating that the server is running.
1. Prints the top 10 bus lines that have most bus stops on their route 
2. Prints the names of every bus stop of the bus line that has the most stops.



   

