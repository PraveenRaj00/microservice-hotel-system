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
                bat 'mvn clean install -f Config-Server/pom.xml'
                bat 'mvn clean install -f Gateway-Service/pom.xml'
                bat 'mvn clean install -f HotelService/pom.xml'
                bat 'mvn clean install -f RatingService/pom.xml'
                bat 'mvn clean install -f Service-Registry/pom.xml'
                bat 'mvn clean install -f User-Service/pom.xml'
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
