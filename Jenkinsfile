pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/PraveenRaj00/microservice-hotel-system.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -f pom.xml'
                sh 'mvn clean install -f pom.xml'
                sh 'mvn clean install -f HotelService/pom.xml'
                sh 'mvn clean install -f RatingService/pom.xml'
                sh 'mvn clean install -f Service-Registry/pom.xml'
                sh 'mvn clean install -f User-Service/pom.xml'
                // Add more Maven commands for each microservice
            }
        }
    }

    post {
        success {
            echo 'Microservices build successful!'
        }
        failure {
            echo 'Microservices build failed.'
        }
    }
}
