pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/PraveenRaj00/microservice-hotel-system.git']]])
            }
        }

        stage('Build') {
            steps {
                script {
                    def mvnHome = tool 'Maven'

                    // List of all microservices
                    def allServices = ['HotelService', 'RatingService', 'Service-Registry', 'User-Service', 'Gateway-Service', 'Config-Server']

                    // Build all microservices
                    parallel "Build-All-Services": {
                        for (service in allServices) {
                            dir(service) {
                                if (isUnix()) {
                                    sh "'${mvnHome}/bin/mvn' clean install"
                                } else {
                                    bat(/"${mvnHome}\bin\mvn" clean install/)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    post {
        alway{
            junit '**/target/surefire-reports/*.xml'
            cleanWs()
        }
        success {
            echo 'Microservices build successful!'
        }
        failure {
            echo 'Microservices build failed.'
        }
    }
}
