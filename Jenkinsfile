pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-username/your-repository.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -f Config-Server/pom.xml'
                sh 'mvn clean install -f Gateway-Service/pom.xml'
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
