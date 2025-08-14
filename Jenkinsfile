pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')  // DockerHub username/password
        SONAR_TOKEN = credentials('sonar')               // SonarQube token
        EMAIL_RECIPIENT = credentials('email')           // Email credentials
    }

    stages {
        stage('SonarQube Analysis') {
            steps {
                sh """
                cd backend
                mvn clean verify sonar:sonar -Dsonar.login=$SONAR_TOKEN -Dsonar.host.url=http://localhost:9000
                """
            }
        }

        stage('OWASP Scan') {
            steps {
                dependencyCheck additionalArguments: '--project AgroServe --scan backend'
            }
        }

        stage('Trivy Docker Scan') {
            steps {
                sh 'trivy image sumitapex761/agroserve-backend:latest || true'
                sh 'trivy image sumitapex761/agroserve-frontend:latest || true'
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker build -t sumitapex761/agroserve-backend:latest ./backend'
                sh 'docker build -t sumitapex761/agroserve-frontend:latest ./frontend'
            }
        }

        stage('Push Docker Images') {
            steps {
                sh """
                echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
                docker push sumitapex761/agroserve-backend:latest
                docker push sumitapex761/agroserve-frontend:latest
                """
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
            echo 'Pipeline finished.'
        }
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
