pipeline {
    agent any

    stages {
        stage("Preparation") {
            steps {
                checkout scm
                gradleHome = tool "Gradle 4"
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
        failure {
            script {
                currentBuild.result = 'FAILURE'
            }
        }

        always {
            step([$class: 'Mailer',
                notifyEveryUnstableBuild: true,
                recipients: "kess@tut.by",
                sendToIndividuals: true])
        }
     }
}