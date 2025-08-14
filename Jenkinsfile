pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('sonar')           // SonarQube token
        EMAIL_RECIPIENT = credentials('email')       // Email credentials
    }

    // stages {
    //     stage('SonarQube Analysi') {
    //         steps {
    //             sh """
    //             cd backend
    //             mvn clean verify sonar:sonar -Dsonar.login=$SONAR_TOKEN -Dsonar.host.url=http://192.168.106.142:9000  -DskipTests             """
    //         }
    //     }

    //     stage('OWASP Scan') {
    //         steps {
    //             dependencyCheck(
    //                 odcInstallation: 'DP-Check',  // Name of your OWASP installation in Jenkins
    //                 additionalArguments: '--project AgroServe --scan backend'
    //             )
    //         }
    //     }

    //     stage('Trivy Docker Scan') {
    //         steps {
    //             sh 'trivy image sumitapex761/agroserve-backend:latest || true'
    //             sh 'trivy image sumitapex761/agroserve-frontend:latest || true'
    //         }
    //     }

        stage('Build Docker Images') {
            steps {
                sh 'docker build -t sumitapex761/agroserve-backend:latest ./backend'
                sh 'docker build -t sumitapex761/agroserve-frontend:latest ./frontend'
            }
        }

        stage('Push Docker Images') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker', usernameVariable: 'DOCKERHUB_USR', passwordVariable: 'DOCKERHUB_PSW')]) {
                    sh """
                    echo $DOCKERHUB_PSW | docker login -u $DOCKERHUB_USR --password-stdin
                    docker push sumitapex761/agroserve-backend:latest
                    docker push sumitapex761/agroserve-frontend:latest
                    """
                }
            }
        }

        stage('Email Notification') {
            steps {
                mail to: "$EMAIL_RECIPIENT",
                     subject: "AgroServe Build Notification - ${currentBuild.fullDisplayName}",
                     body: "Build Status: ${currentBuild.currentResult}\nCheck Jenkins for details."
            }
        }
    }
 

    post {
        always {
            echo 'Pipeline Finish.'
        }
        success {
            echo 'Build Succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
