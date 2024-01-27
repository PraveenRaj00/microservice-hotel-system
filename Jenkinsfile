pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your Git repository
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/PraveenRaj00/microservice-hotel-system.git']]])
            }
        }

        stage('Build') {
            steps {
                script {
                    def mvnHome = tool 'Maven'
                    def allServices = ['HotelService', 'RatingService', 'Service-Registry', 'User-Service']
                    def selectedServices = ['HotelService', 'RatingService']

                    parallel (
                        "Build-All-Services": {
                            for (service in allServices) {
                                dir(service) {
                                    if (isUnix()) {
                                        sh "'${mvnHome}/bin/mvn' clean install"
                                    } else {
                                        bat(/"${mvnHome}\bin\mvn" clean install/)
                                    }
                                }
                            }
                        },
                        "Build-Selected-Services": {
                            for (service in selectedServices) {
                                dir(service) {
                                    if (isUnix()) {
                                        sh "'${mvnHome}/bin/mvn' clean install"
                                    } else {
                                        bat(/"${mvnHome}\bin\mvn" clean install/)
                                    }
                                }
                            }
                        }
                    )
                }
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
//Script for Test
pipeline {
//     agent any

//     stages {
//         stage('Checkout') {
//             steps {
//                 // Checkout the code from your Git repository
//                 checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/PraveenRaj00/microservice-hotel-system.git']]])
//             }
//         }

//         stage('Build and Test') {
//             steps {
//                 script {
//                     def mvnHome = tool 'Maven'
//                     def allServices = ['HotelService', 'RatingService', 'Service-Registry', 'User-Service']
//                     def selectedServices = ['HotelService', 'RatingService']

//                     parallel (
//                         "Build-All-Services": {
//                             for (service in allServices) {
//                                 dir(service) {
//                                     if (isUnix()) {
//                                         sh "'${mvnHome}/bin/mvn' clean install"
//                                     } else {
//                                         bat(/"${mvnHome}\bin\mvn" clean install/)
//                                     }
//                                 }
//                             }
//                         },
//                         "Build-Selected-Services": {
//                             for (service in selectedServices) {
//                                 dir(service) {
//                                     if (isUnix()) {
//                                         sh "'${mvnHome}/bin/mvn' clean install"
//                                     } else {
//                                         bat(/"${mvnHome}\bin\mvn" clean install/)
//                                     }
//                                 }
//                             }
//                         }
//                     )

//                     // Run tests for selected microservices
//                     parallel (
//                         "Test-HotelService": {
//                             if ('HotelService' in selectedServices) {
//                                 dir('HotelService') {
//                                     if (isUnix()) {
//                                         sh "'${mvnHome}/bin/mvn' test"
//                                     } else {
//                                         bat(/"${mvnHome}\bin\mvn" test/)
//                                     }
//                                 }
//                             }
//                         },
//                         "Test-RatingService": {
//                             if ('RatingService' in selectedServices) {
//                                 dir('RatingService') {
//                                     if (isUnix()) {
//                                         sh "'${mvnHome}/bin/mvn' test"
//                                     } else {
//                                         bat(/"${mvnHome}\bin\mvn" test/)
//                                     }
//                                 }
//                             }
//                         }
//                     )
//                 }
//             }
//         }
//     }

//     post {
//         success {
//             echo 'Microservices build and tests successful!'
//         }
//         failure {
//             echo 'Microservices build and tests failed.'
//         }
//     }
// }
