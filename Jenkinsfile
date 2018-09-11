pipeline {
    agent any

    environment {
        gradleHome = tool "Gradle 4"
    }

    stages {
        stage("Checkout") {
            steps {
                checkout scm
            }
        }

        stage("Build") {
            steps {
                bat("${gradleHome}/bin/gradle clean :exmo-api:jar :coinmarketcap-api:jar")
                archiveArtifacts artifacts: '**/build/libs/*-api*.jar'
            }
        }

        stage("Publish") {
            steps {
                bat ("${gradleHome}/bin/gradle publish")
            }
        }
    }

    post {
        always {
              step([$class: 'Mailer',
                notifyEveryUnstableBuild: true,
                recipients: "kess@tut.by kess2007@mail.ru",
                sendToIndividuals: true])
        }
    }
}

